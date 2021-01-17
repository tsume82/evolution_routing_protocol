import os

def get_execution_time(filename):
    data = open(filename, 'r')

    for line in data:
        if 'Overall execution:' in line:
            line = line.split(';')
            time_exec = line[0].replace('|     Overall execution: sum=', '').replace(' s', '')
            time_exec = float(time_exec)
            #print(time_exec)
            return time_exec

DIRECTORY = '../'
files = {}
for dirname in os.listdir(DIRECTORY):
    dirpath = os.path.join(DIRECTORY, dirname)
    if os.path.isdir(dirpath) and ('Epidemic' in dirname or 'Prophet' in dirname):
        print(dirpath)
        files[dirname] = []
        end_f = ''
        if 'Default' in dirpath:
            end_f += 'mapdefault'
        elif 'Helsinki' in dirpath:
            end_f += 'maphelsinki'
        elif 'Manhattan' in dirpath:
            end_f += 'mapmanhattan'
        if '100' in dirpath:
            end_f += '_host100'
        elif '40' in dirpath:
            end_f += '_host40'
        if 'Epidemic' in dirpath:
            end_f += '_protepidemic.txt'
        elif 'Prophet' in dirpath:
            end_f += '_protprophet.txt'

        for file in os.listdir(dirpath):
            if file.endswith(end_f):
                files[dirname].append(file)

out_file = open('./execution_times.csv', 'w')
for dir in files:
    print(dir)
    print(len(files[dir]))
    all_times = []
    for file in files[dir]:
        filepath = os.path.join(DIRECTORY, dir, file)
        all_times.append(str(get_execution_time(filepath)))
    #print(';'.join(all_times))
    out_file.write(dir + ';' + ';'.join(all_times) + '\n')
    print(round(sum([float(v) for v in all_times])/len(all_times)/60/60, 2))


