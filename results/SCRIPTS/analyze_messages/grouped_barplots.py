# -*- coding: utf-8 -*-

import matplotlib.pyplot as plt
import numpy as np
from random import random

import os
 
# see:
# https://python-graph-gallery.com/11-grouped-barplot/
# https://matplotlib.org/3.3.1/api/_as_gen/matplotlib.pyplot.bar.html
#------------------------------------------------------#

def grouped_barplots(data_to_plot, legend_labels, group_labels, log=False):
    prop_cycle = plt.rcParams['axes.prop_cycle']
    colors = prop_cycle.by_key()['color']
     
    barWidth = 0.25
    
    fig, axs = plt.subplots(1,1)#,figsize=(10,8))
    tick_positions = []

    for i in range(len(legend_labels)):
        data = []
        err = []
        for j in range(len(group_labels)):
            data += [np.mean(data_to_plot[i][j])]
            if log:
                m = np.mean(data_to_plot[i][j])
                s = np.std(data_to_plot[i][j])
                if m == 0:
                    tmp = 0
                else:
                    # see the correction here: https://faculty.washington.edu/stuve/log_error.pdf
                    tmp = 0.434*s/m
                err += [tmp]
                #err += [0.434*np.divide(np.std(data_to_plot[i][j]),np.mean(data_to_plot[i][j]))]
            else:
                err += [np.std(data_to_plot[i][j])]
        if i == 0:
            tick_positions += [np.arange(len(data))]
        else:
            tick_positions += [[x + barWidth for x in tick_positions[i-1]]]
        plt.bar(tick_positions[i], data, yerr=err, color=colors[i], ecolor=colors[i], label=legend_labels[i], \
            width=barWidth, edgecolor='white', capsize=2, alpha=0.5, log=log)
                    
    # Add xticks on the middle of the group bars
    plt.xticks([r + barWidth * (len(legend_labels)-1)/2 for r in range(len(data))], group_labels, fontsize=fontsize) #rotation=90)
    
    if log:
        plt.ylabel('No. messages (log)', fontsize=fontsize)
        xmin, xmax, ymin, ymax = plt.axis()
        if ymin < 1:
            plt.ylim(1,max(max(max(data_to_plot)))*1.1)
        else:
            plt.ylim(1,ymax)
        plt.ylim(1,1e6)
    else:
        plt.ylabel('No. messages', fontsize=fontsize)
        plt.ticklabel_format(style='sci', axis='y', scilimits=(0,0))
    
    plt.legend(loc=1,prop={'size': fontsize})
    #plt.show()
    
    return fig
    
#------------------------------------------------------#

def get_data_from_file(file):
    data = []
    for i in range(7):
        data.append(0)
    with open(file) as fp:
        for line in fp:
            if line.startswith('created'):
                data[0] = int(line.split(': ')[1])
            elif line.startswith('started'):
                data[1] = int(line.split(': ')[1])
            elif line.startswith('relayed'):
                data[2] = int(line.split(': ')[1])
            elif line.startswith('aborted'):
                data[3] = int(line.split(': ')[1])
            elif line.startswith('dropped'):
                data[4] = int(line.split(': ')[1])
            elif line.startswith('removed'):
                data[5] = int(line.split(': ')[1])
            elif line.startswith('delivered'):
                data[6] = int(line.split(': ')[1])
    return data

#------------------------------------------------------#

legend_labels = ['Baseline', 'Evolution']
#group_labels = ['Created', 'Started', 'Relayed', 'Aborted', 'Dropped', 'Removed', 'Delivered']
group_labels = ['Crt', 'Srt', 'Rel', 'Abt', 'Drp', 'Rem', 'Del']

RESULT_FOLDER='../..'

fontsize=14

scenarios = [ \
    ['Baseline_epidemic_default_40hosts', 'Epidemic_Default_40hosts'], \
    ['Baseline_epidemic_default_100hosts', 'Epidemic_Default_100hosts'], \
    ['Baseline_epidemic_helsinki_40hosts', 'Epidemic_Helsinki_40hosts'], \
    ['Baseline_epidemic_helsinki_100hosts', 'Epidemic_Helsinki_100hosts'], \
    ['Baseline_epidemic_manhattan_40hosts', 'Epidemic_Manhattan_40hosts'], \
    ['Baseline_epidemic_manhattan_100hosts', 'Epidemic_Manhattan_100hosts'], \
    ['Baseline_prophet_default_40hosts', 'Prophet_Default_40hosts'], \
    ['Baseline_prophet_default_100hosts', 'Prophet_Default_100hosts'], \
    ['Baseline_prophet_helsinki_40hosts', 'Prophet_Helsinki_40hosts'], \
    ['Baseline_prophet_helsinki_100hosts', 'Prophet_Helsinki_100hosts'], \
    ['Baseline_prophet_manhattan_40hosts', 'Prophet_Manhattan_40hosts'], \
    ['Baseline_prophet_manhattan_100hosts', 'Prophet_Manhattan_100hosts'] \
    ]

for scenario in scenarios:
    data_to_plot = []

    baseline_folder = RESULT_FOLDER + '/' + scenario[0]
    for subdir, dirs, files in os.walk(baseline_folder):
        tmp = []
        for file in files:
            if file.endswith('MessageStatsReport.txt'):
                data = get_data_from_file(baseline_folder + '/' + file)
                tmp.append(data)
        tmp = np.array(tmp).T.tolist()
        data_to_plot.append(tmp)
        
    evo_folder = RESULT_FOLDER + '/' + scenario[1]
    for subdir, dirs, files in os.walk(evo_folder):
        tmp = []
        for file in files:
            if file.endswith('MessageStatsReport.txt'):
                data = get_data_from_file(evo_folder + '/' + file)
                tmp.append(data)
        tmp = np.array(tmp).T.tolist()
        data_to_plot.append(tmp)
    
    fig = grouped_barplots(data_to_plot, legend_labels, group_labels, True)
    fig.savefig('barplot_' + scenario[1] + '.pdf', format='pdf')#, dpi=100)

'''
num_samples = 10
minValue=0
maxValue=100

data_to_plot = []
for i in range(len(legend_labels)):
    data_to_plot.append([])
    for j in range(len(group_labels)):
        data_to_plot[i].append((minValue+(maxValue-minValue)*np.random.rand(num_samples)))

print('---')
print(len(data_to_plot))
print(len(data_to_plot[0]))
print(len(data_to_plot[0][0]))

fig = grouped_barplots(data_to_plot, legend_labels, group_labels)
fig.savefig('barplot.pdf', format='pdf')#, dpi=100)
'''
