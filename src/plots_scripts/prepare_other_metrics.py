import pandas as pd

files = {
    'Delivery probability': '../results/delivery_prob_median_min_max.csv',
    'Overhead ratio': '../results/overhead_ratio_median_min_max.csv',
    'Latency (avg)': '../results/latency_avg_median_min_max.csv',
    'Hop count (avg)': '../results/hopcount_avg_median_min_max.csv',
    'Buffer time (avg)': '../results/buffertime_avg_median_min_max.csv'
}

other_metrics_gp = {}
other_metrics_baseline = {}

for file in files:
    dataframe = pd.read_csv(files[file], sep=';')

    tmp_gp = {}
    tmp_baseline = {}

    for index, row in dataframe.iterrows():
        if 'Baseline_' in row['scenario']:
            tmp_baseline[row['scenario'].replace('Baseline', '')] = row['median']
        else:
            tmp_gp[row['scenario']] = row['median']

    other_metrics_gp[file] = []
    for key in sorted(tmp_gp.keys()) :
        other_metrics_gp[file].append(str(tmp_gp[key]))

    other_metrics_baseline[file] = []
    for key in sorted(tmp_baseline.keys()) :
        other_metrics_baseline[file].append(str(tmp_baseline[key]))

out_file_name = '../../results/other_metrics_gp.csv'
out_file = open(out_file_name, 'w')
for metric in other_metrics_gp:
    out_file.write(metric + ';' + ';'.join(other_metrics_gp[metric]) + '\n')
out_file.close()

out_file_name = '../../results/other_metrics_baseline.csv'
out_file = open(out_file_name, 'w')
for metric in other_metrics_baseline:
    out_file.write(metric + ';' + ';'.join(other_metrics_baseline[metric]) + '\n')
out_file.close()
