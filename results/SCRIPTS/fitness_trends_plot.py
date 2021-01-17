import os
import statistics
import pandas as pd
import matplotlib.pyplot as plt

DIRECTORY = '../'


def plot_fitness_trend(filepath, save=False, save_filepath=None):
    df = pd.read_csv(filepath, delimiter=';')
    df = df.drop(df.columns[0], axis=1)
    best = []
    mean = []
    worst = []
    for index, row in df.iterrows():
        best_v = 0
        worst_v = 1000
        counter = 0
        total = 0
        for v in df.iloc[index].values:
            if v > best_v:
                best_v = v
            if v < worst_v:
                worst_v = v
            total += v
            counter += 1
        best.append(best_v)
        worst.append(worst_v)
        mean.append(total/counter)
    plt.plot(worst, '-ok', color='firebrick', label='Worst fitness per generation')
    plt.plot(mean, '-ok', color='orange', label='Mean fitness per generation')
    plt.plot(best, '-ok', color='green', label='Best fitness per generation')
    plt.xlabel('Generation')
    plt.ylabel('Fitness')
    plt.legend(loc='lower right')
    if save:
        plt.savefig(save_filepath)
        plt.clf()
    else:
        plt.show()
        plt.clf()


def plot_best_fitness_trend(filenames, dirpath, save=False, save_filepath=None):
    indexes = [v for v in range(1,101)]
    columns = [c for c in range(1,11)]
    df_new = pd.DataFrame(index=indexes, columns=columns)
    #print(len(df_new.index))
    curr_f = 1
    for filename in filenames:
        df = pd.read_csv(os.path.join(dirpath, filename), names=['generation', filename.replace(DIRECTORY, '')], delimiter=';')
        current_val = df.T.iloc[1].values
        for i in range(0,len(df.T.iloc[1].values)):
            df_new.at[i+1, curr_f] = current_val[i]
        curr_f += 1

    df = df_new
    df = df.fillna(99999999)
    big = []
    for index, row in df.iterrows():
        mylist = []
        for v in df.loc[index].values:
            if v != 99999999:
                mylist.append(v)
        if len(mylist) > 1:
            m = statistics.mean(mylist)
            std_dev = statistics.stdev(mylist, xbar = m)
            big.append((m,std_dev))
        elif len(mylist) > 0:
            big.append((mylist[0],0.0))
    #print(len(big))

    count = 1
    generations = []
    avg_fitness = []
    up_std_fitness = []
    down_std_fitness = []
    for (m, s) in big:
        generations.append(count)
        avg_fitness.append(m)
        up_std_fitness.append(m+s)
        down_std_fitness.append(m-s)
        count += 1
    plt.plot(generations,avg_fitness, linestyle='-', marker=".", color='darkorange') # cornflowerblue
    plt.plot(generations,up_std_fitness, linestyle='')
    plt.plot(generations,down_std_fitness, linestyle='')
    plt.fill_between(generations,down_std_fitness, up_std_fitness, color='darkorange', alpha=0.5) #alpha=0.5    lightblue navajowhite
    #plt.plot(generations,down_std_fitness, linestyle='-', linewidth=0.7, color='darkorange') # cornflowerblue
    #plt.plot(generations,up_std_fitness, linestyle='-', linewidth=0.7, color='darkorange') # cornflowerblue
    plt.xlabel('Generation', fontsize=13)
    plt.ylabel('Delivery probability', fontsize=13)
    ax = plt.gca()
    ax.tick_params(axis="x", labelsize=12)
    ax.tick_params(axis="y", labelsize=12)

    delivery_prob_values_file = './delivery_prob.csv'
    df_delivery_prob = pd.read_csv(delivery_prob_values_file, sep=';')
    df_delivery_prob['Configuration'] = df_delivery_prob['Configuration'].str.lower()
    config_name = 'baseline_' + dirpath.replace('../', '')

    print(config_name)

    baseline_del_prob = df_delivery_prob.loc[df_delivery_prob['Configuration'] == config_name.lower()]
    #print(baseline_del_prob.median(axis=1).values[0])

    plt.hlines(baseline_del_prob.median(axis=1).values[0], 0, len(generations), linestyle='--', color='dodgerblue')    # red

    title = ''
    if 'Epidemic' in DIRECTORY:
        title += 'Epidemic routing protocol - '
    elif 'Prophet' in DIRECTORY:
        title += 'PRoPHET routing protocol - '
    if 'Helsinki' in DIRECTORY:
        title += 'Helsinki map - '
    elif 'Default' in DIRECTORY:
        title += 'Default map - '
    if '40hosts' in DIRECTORY:
        title += '40 hosts'
    elif '100hosts' in DIRECTORY:
        title += '100 hosts'
    # plt.title(title)

    if save:
        plt.savefig(save_filepath)
        plt.clf()
    else:
        plt.show()
        plt.clf()


for dirname in os.listdir(DIRECTORY):
    dirpath = os.path.join(DIRECTORY, dirname)
    if os.path.isdir(dirpath) and ('Epidemic' in dirname or 'Prophet' in dirname) and 'Baseline' not in dirname:
        best_fitness_files = []
        for file in os.listdir(dirpath):
            if file.endswith('_all_fitness.csv'):
                print('plot fitness trend')
                # plot_fitness_trend(os.path.join(dirpath,file), save=True, save_filepath=os.path.join(dirpath,file.replace('all_fitness.csv', 'fitness_trend.eps')))
            elif file.endswith('_best_fitness.csv'):
                best_fitness_files.append(file)

        print('plot best fitness trend')
        if len(best_fitness_files) > 0:
            plot_best_fitness_trend(best_fitness_files, dirpath, save=True, save_filepath=os.path.join('./', dirpath.replace('../', '').lower() + '_means.pdf'))

