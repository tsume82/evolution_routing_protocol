import os

DIRECTORY = '../../results'
files = {}
for dirname in os.listdir(DIRECTORY):
    dirpath = os.path.join(DIRECTORY, dirname)
    if os.path.isdir(dirpath) and ('Epidemic' in dirname or 'Prophet' in dirname or 'Baseline' in dirname):
        print(dirpath)
        files[dirname] = []

        for file in os.listdir(dirpath):
            if file.endswith('MessageStatsReport.txt'):
                files[dirname].append(file)

metrics = {
    'created': {},
    'started': {},
    'relayed': {},
    'aborted': {},
    'dropped': {},
    'removed': {},
    'delivered': {},
    'delivery_prob': {},
    'response_prob': {},
    'overhead_ratio': {},
    'latency_avg': {},
    'latency_med': {},
    'hopcount_avg': {},
    'hopcount_med': {},
    'buffertime_avg': {},
    'buffertime_med': {}
}
for dir in files:
    print(dir)
    print(len(files[dir]))
    for file in files[dir]:
        filepath = os.path.join(DIRECTORY, dir, file)
        data = open(filepath, 'r')
        for line in data:
            line = line.replace(':', '').split(' ')
            key = line[0]
            if key in metrics:
                if dir not in metrics[key]:
                    metrics[key][dir] = []
                metrics[key][dir].append(line[1].replace('\n', ''))

print(metrics['delivery_prob'])

for metric in metrics:
    out_file = open('../../results/' + metric + '.csv', 'w')
    out_file.write('Configuration;'+';'.join(['value'+str(i+1) for i in range(10)])+'\n')
    for key in metrics[metric]:
        if len(metrics[metric][key]) < 10:
            metrics[metric][key] = metrics[metric][key]+['']*(10-len(metrics[metric][key]))
        out_file.write(key + ';' + ';'.join(metrics[metric][key]) + '\n')
