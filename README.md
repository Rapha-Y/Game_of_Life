# Game of Life
Game of life implementation through multiple threads.

Game of life rules:

A. Living cells with 2 or 3 neighbors survive;
B. Dead cells with 3 neighbors become living;
C. Otherwise, living cells die and dead cells stay dead.

Highlife variation rules:

A. Living cells with 2 or 3 neighbors survive;
B. Dead cells with 3 or 6 neighbors become living;
C. Otherwise, living cells die and dead cells stay dead.

# Results
Results for a machine with the Intel(R) Core(TM) i5-8250U processor, 4 cores, and 1.3 GB of main memory available.

Game of life:

1 thread: 292.151000
2 threads: 196.362000
3 threads: 131.750000
4 threads: 110.583000

Highlife:

1 thread: 285.704000
2 threads: 187.240000
3 threads: 141.230000
4 threads: 114.325000

Game of life (Java):

1 thread: 117503
2 threads: 149510
4 threads: 133783
8 threads: 150865

Highlife (Java):

1 thread: 123915
2 threads: 145002
4 threads: 109916
8 threads: 138079