# -*- coding: utf-8 -*-

import matplotlib.pyplot as plt
import numpy as np
from random import random

#------------------------------------------------------#

def matrixScatter2D(data_to_plot, data_labels, symmetric=True):
    
    prop_cycle = plt.rcParams['axes.prop_cycle']
    colors = prop_cycle.by_key()['color']

    n = len(data_to_plot)
    fig, axs = plt.subplots(n, n, figsize=(10,8))#, sharex=True)#, sharey=True)
    
    color_idx = 0
    
    for i in range(n):
        for j in range(n):
            if symmetric or i <= j:
                axs[j,i].plot(data_to_plot[i], data_to_plot[j], 'o', markersize=5, fillstyle='none', color=colors[color_idx % len(colors)])
                #axs[j,i].set(xlabel=data_labels[i], ylabel=data_labels[j])
                axs[j,i].set(xlabel=data_labels[i])
                axs[j,i].set_ylabel(data_labels[j])#, rotation=0, labelpad=40)
                #axs[j,i].set(adjustable='box', aspect='equal')
                axs[j,i].tick_params('x', labelrotation=90)
            else:
                axs[j,i].set_visible(False)
        color_idx += 1
            
    # Hide x labels and tick labels for top plots and y ticks for right plots.
    for ax in axs.flat:
        ax.label_outer()
    fig.align_xlabels(axs)
    fig.align_ylabels(axs)
    
    plt.tight_layout(pad=0.05, h_pad=0.1, w_pad=0.1)
    
    return fig

#------------------------------------------------------#

def matrixScatter2D_2(data_to_plot1, data_to_plot2, data_labels, symmetric=True):
    
    prop_cycle = plt.rcParams['axes.prop_cycle']
    colors = prop_cycle.by_key()['color']

    n = len(data_to_plot_1)
    fig, axs = plt.subplots(n, n,figsize=(10,8))#, sharex=True)#, sharey=True)
    
    color_idx = 0
    
    for i in range(n):
        for j in range(n):
            if symmetric or i <= j:
                l1=axs[j,i].plot(data_to_plot1[i], data_to_plot1[j], 'o', markersize=5, fillstyle='none')#, color=colors[color_idx % len(colors)])
                l2=axs[j,i].plot(data_to_plot2[i], data_to_plot2[j], 'o', markersize=5, fillstyle='none')#, color=colors[color_idx % len(colors)])
                #axs[j,i].set(xlabel=data_labels[i], ylabel=data_labels[j])
                axs[j,i].set(xlabel=data_labels[i])
                axs[j,i].set_ylabel(data_labels[j])#, rotation=0, labelpad=40)
                #axs[j,i].set(adjustable='box', aspect='equal')
                axs[j,i].tick_params('x', labelrotation=90)
            else:
                axs[j,i].set_visible(False)
                
        color_idx += 1
    
    # common legend
    line_labels = ['Baseline', 'Evolution']
    fig.legend([l1,l2], labels=line_labels, loc="center right")
            
    # Hide x labels and tick labels for top plots and y ticks for right plots.
    for ax in axs.flat:
        ax.label_outer()
    fig.align_xlabels(axs)
    fig.align_ylabels(axs)
    
    plt.tight_layout(pad=0.05, h_pad=0.1, w_pad=0.1)
    
    return fig

#------------------------------------------------------#

import csv

data_labels_1 = []
data_to_plot_1 = []
with open('other_metrics_gp.csv', 'rU') as csvfile:
    reader = csv.reader(csvfile, delimiter=';', quotechar='|')
    for row in reader:
        data_labels_1.append(row[0])
        data_to_plot_1.append(list(map(float,row[1:])))

fig=matrixScatter2D(data_to_plot_1, data_labels_1, False)
fig.savefig('metrics_gp.eps', format='eps', dpi=100)

data_labels_2 = []
data_to_plot_2 = []
with open('other_metrics_baseline.csv', 'rU') as csvfile:
    reader = csv.reader(csvfile, delimiter=';', quotechar='|')
    for row in reader:
        data_labels_2.append(row[0])
        data_to_plot_2.append(list(map(float,row[1:])))

fig=matrixScatter2D(data_to_plot_2, data_labels_2, False)
fig.savefig('metrics_baseline.eps', format='eps', dpi=100)

fig=matrixScatter2D_2(data_to_plot_2, data_to_plot_1, data_labels_2, False)
fig.savefig('metrics.eps', format='eps', dpi=100)

#plt.show()

'''

minValue=13777.95
maxValue=15893.29
#minValue=0
#maxValue=1
    
numdata = 5
numsamples = 8
data_to_plot = []
data_labels = []
for i in range(numdata):
    #minValue=100*np.random.rand()
    #maxValue=minValue+(i+1)*100*np.random.rand()
    data_to_plot.append((minValue+(maxValue-minValue)*np.random.rand(numsamples)))
    data_labels.append('data'+str(i))
'''
