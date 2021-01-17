import os

DIRECTORY = '../'
files = {}
for dirname in os.listdir(DIRECTORY):
    dirpath = os.path.join(DIRECTORY, dirname)
    if os.path.isdir(dirpath) and ('Epidemic' in dirname or 'Prophet' in dirname) and 'Baseline' not in dirname:
        print(dirpath)
        files[dirname] = []

        for file in os.listdir(dirpath):
            if 'Tree' in file:
                files[dirname].append(file)


for dir in files:
    print(dir)
    print(len(files[dir]))
    for file in files[dir]:
        res_file = open(os.path.join(DIRECTORY, dir, 'count_nodes.csv'), 'w')
        input = open(os.path.join(DIRECTORY, dir, file),'r')
        for i in input:
            tmp = i.split('(')
            nodes = 0
            for t in tmp:
                r = t.split(',')
                nodes += len(r)
            print(nodes)
        res_file.write(file + ';' + str(nodes) + '\n')
        input.close()
        res_file.close()

