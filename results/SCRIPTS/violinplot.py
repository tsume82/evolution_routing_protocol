# -*- coding: utf-8 -*-

import matplotlib.colors as mplcolors
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

#------------------------------------------------------#

def violinplot_with_boxplot(data_to_plot, data_labels, fig_id='test', logScale=False):
    plt.figure(fig_id)

    colors = []

    for i in range(len(data_to_plot)):
        violinplot = plt.violinplot(data_to_plot[i], positions=[i], showmeans=False, showmedians=True, showextrema=True)
        violin_color = mplcolors.to_hex(violinplot['bodies'][0].get_facecolor()[0], keep_alpha=False)
        colors.append(violin_color)
        
        for pc in violinplot['bodies']:
            pc.set_alpha(0.25)
            pc.set_edgecolor(violin_color)
        violinplot['cbars'].set_alpha(0.0)
        violinplot['cmins'].set_alpha(0.0)
        violinplot['cmaxes'].set_alpha(0.0)
        violinplot['cmedians'].set_alpha(0.0)
        
        flierprops = dict(marker='o', markerfacecolor=violin_color, markersize=6, \
            linestyle='none', markeredgecolor=violin_color, alpha=1.0)

        boxplot = plt.boxplot(data_to_plot[i], positions=[i], flierprops=flierprops, labels=(data_labels[i],),
            patch_artist=True, showmeans=False, showfliers=True, showcaps=False, widths=0.25)

        for pc in boxplot['boxes']:
            pc.set_edgecolor(violin_color)
            pc.set_facecolor(violin_color)
            pc.set_alpha(0.50)
        for pc in boxplot['caps']:
            pc.set_color(violin_color)
        for pc in boxplot['whiskers']:
            pc.set_color(violin_color)
        for pc in boxplot['fliers']:
            pc.set_color(violin_color)
        for pc in boxplot['medians']:
            pc.set_alpha(1.0)
            pc.set_color(violin_color)
            pc.set_linewidth(3)
    
    if logScale:
        plt.yscale('log')

    return plt, colors

DATA_FILE = './delivery_prob.csv'
dataframe = pd.read_csv(DATA_FILE, sep=';').fillna(0)
dataframe['Configuration'] = dataframe['Configuration'].str.lower()

data_base = []
data_evo = []
for index, row in dataframe.iterrows():
    if 'baseline' in row['Configuration']:
        evo_name = row['Configuration'].replace('baseline_', '')
        evo_row = dataframe.loc[dataframe['Configuration'] == evo_name]
        data_base = []
        for column in row:
            if type(column) != str:
                data_base.append(column)

        data_evo = []
        for column in evo_row:
            if column != 'Configuration':
                data_evo.append(evo_row[column].values[0])

        title = ''
        if 'epidemic' in row['Configuration']:
            title += 'Epidemic routing protocol - '
        if 'prophet' in row['Configuration']:
            title += 'PRoPHET routing protocol - '
        if 'default' in row['Configuration']:
            title += 'Default map - '
        if 'helsinki' in row['Configuration']:
            title += 'Helsinki map - '
        if 'manhattan' in row['Configuration']:
            title += 'Manhattan map - '
        if '40' in row['Configuration']:
            title += '40 hosts'
        if '100' in row['Configuration']:
            title += '100 hosts'
        data_to_plot = [data_base,data_evo]
        data_labels = ['Baseline','Evolution']
        plot, _ = violinplot_with_boxplot(data_to_plot, data_labels, fig_id=title)
        plot.ylabel('Delivery probability', fontsize=14)
        ax = plt.gca()
        ax.tick_params(axis="x", labelsize=13)
        ax.tick_params(axis="y", labelsize=13)

        plot.savefig('./' + evo_name + '_violin.pdf')
        #plot.show()

        current = ''
        data_base = []
        data_evo = []
