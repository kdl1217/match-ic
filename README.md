# match-ic

> 英卡数据流竞赛

## datax-job

> 使用datax工具，将sample.txt转存到MongoDB服务器。

```bash
# su - root
sh> cd ~/datax/bin
sh> python ./datax.py ~/datax-job/txt2mongo.json
# 1200w result
2020-03-01 20:27:24.351 [job-0] INFO  JobContainer - PerfTrace not enable!
2020-03-01 20:27:24.351 [job-0] INFO  StandAloneJobContainerCommunicator - Total 12000000 records, 766222833 bytes | Speed 4.87MB/s, 80000 records/s | Error 0 records, 0 bytes |  All Task WaitWriterTime 102.529s |  All Task WaitReaderTime 17.652s | Percentage 100.00%
2020-03-01 20:27:24.352 [job-0] INFO  JobContainer - 
任务启动时刻                    : 2020-03-01 20:24:54
任务结束时刻                    : 2020-03-01 20:27:24
任务总计耗时                    :                150s
任务平均流量                    :            4.87MB/s
记录写入速度                    :          80000rec/s
读出记录总数                    :            12000000
读写失败总数                    :                   0
# 1.5亿 result
2020-03-02 10:40:56.669 [job-0] INFO  JobContainer - PerfTrace not enable!
2020-03-02 10:40:56.669 [job-0] INFO  StandAloneJobContainerCommunicator - Total 151438501 records, 9824693860 bytes | Speed 4.85MB/s, 78465 records/s | Error 3 records, 78 bytes |  All Task WaitWriterTime 1,351.966s |  All Task WaitReaderTime 257.380s | Percentage 100.00%
2020-03-02 10:40:56.676 [job-0] INFO  JobContainer - 
任务启动时刻                    : 2020-03-02 10:08:46
任务结束时刻                    : 2020-03-02 10:40:56
任务总计耗时                    :               1930s
任务平均流量                    :            4.85MB/s
记录写入速度                    :          78465rec/s
读出记录总数                    :           151438501
读写失败总数                    :                   3
```
