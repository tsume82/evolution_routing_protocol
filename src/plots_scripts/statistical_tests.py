import pandas as pd
from scipy.stats import wilcoxon
from scipy.stats import mannwhitneyu
from scipy.stats import ranksums

DATA_FILE = '../../results/delivery_prob.csv'

def execute_test(test_name, dataset1, dataset2):
    if test_name == 'wilcoxon':
        stat, p = wilcoxon(dataset1, dataset2)
    elif test_name == 'mannwhitneyu':
        stat, p = mannwhitneyu(dataset1, dataset2)
    else:
        stat, p = ranksums(dataset1, dataset2)

    alpha = 0.05
    if p > alpha:
        return stat, p, 'fail to reject H0'
    else:
        return stat, p, 'reject H0'


#dataframe = pd.read_csv(DATA_FILE, header=None, sep=';')
dataframe = pd.read_csv(DATA_FILE, sep=';').fillna(0)
print(dataframe)

out_filename = '../../results/delivery_prob_statistical_tests_results.csv'
out_file = open(out_filename, 'w')
out_file.write('scenario;wilcoxon_stat;wilcoxon_p;wilcoxon_res;mannwhitneyu_stat;mannwhitneyu_p;mannwhitneyu_res;ranksum_stat;ranksum_p;ranksum_res\n')

for index, row in dataframe.iterrows():
    if 'Baseline' in row['Configuration']:
        evo_name = row['Configuration'].replace('Baseline_', '')
        #print(evo_name)
        evo_row = dataframe.loc[dataframe['Configuration'] == evo_name]
        #print(evo_row)
        data_base = []
        for column in row:
            if type(column) != str:
                data_base.append(column)

        data_evo = []
        for column in evo_row:
            if column != 'Configuration':
                data_evo.append(evo_row[column].values[0])
        print(data_evo)
        print(data_base)

        res = [evo_name]
        stat_w, p_w, res_w = execute_test('wilcoxon', data_base, data_evo)
        res.extend([str(stat_w), str(p_w), str(res_w)])

        stat_m, p_m, res_m = execute_test('mannwhitneyu', data_base, data_evo)
        res.extend([str(stat_m), str(p_m), str(res_m)])

        stat_r, p_r, res_r = execute_test('ranksums', data_base, data_evo)
        res.extend([str(stat_r), str(p_r), str(res_r)])

        out_file.write(';'.join(res)+'\n')
out_file.close()
