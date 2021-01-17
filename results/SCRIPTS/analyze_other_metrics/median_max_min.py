import pandas as pd

files = {
    'delivery_prob': '../delivery_prob.csv',
    'overhead_ratio': '../overhead_ratio.csv',
    'latency_avg': '../latency_avg.csv',
    'hopcount_avg': '../hopcount_avg.csv',
    'buffertime_avg': '../buffertime_avg.csv'
}
}

for file in files:
    dataframe = pd.read_csv(files[file], sep=';')

    out_filename = f'./{file}_median_min_max.csv'
    out_file = open(out_filename, 'w')
    out_file.write('scenario;median;min;max\n')

    new_df = dataframe
    new_df['median'] = dataframe.median(axis=1, numeric_only=True)
    new_df['max'] = dataframe.max(axis=1)
    new_df['min'] = dataframe.min(axis=1)
    print(new_df)

    for index, row in new_df.iterrows():
        out_file.write(row['Configuration'] + ';' + str(row['median']) + ';' + str(row['min']) + ';' + str(row['max']) + '\n')
    out_file.close()
