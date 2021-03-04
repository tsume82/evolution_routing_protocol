# Source code for the paper "Genetic Improvement of Routing Protocols for Delay Tolerant Networks".

To appear on ACM Transactions on Evolutionary Learning and Optimization.

The repository contains the following files:

- **results**: The raw data for all the experiments reported in the paper, with the related scripts for postprocessing and figure generation.
- **src**: The toolchain for dynamic class generation, starting from a tree evolved by Genetic Programming in [Jenetics](https://jenetics.io), simulation execution in [The ONE](http://akeranen.github.io/the-one/).
- **the-one**: The original The ONE simulator, in addition to custom implementations of [PRoPHET+](https://ieeexplore.ieee.org/document/5474683?reload=true&arnumber=5474683) and [PRoPHET with Efficient Evict Policy](https://link.springer.com/article/10.1007/s41870-019-00359-x). This folder also contains the configuration files needed to run these protocols. 

