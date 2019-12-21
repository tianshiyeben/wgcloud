package com.wgcloud;

import com.wgcloud.entity.*;
import org.hyperic.sigar.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @ClassName:SigarUtil.java
 * @version V1.0
 * @author: wgcloud
 * @date: 2019年11月16日
 * @Description: SigarUtil.java
 * @Copyright: 2019 wgcloud. All rights reserved.
 *
 */
public class SigarUtil {

    private static Logger logger  = LoggerFactory.getLogger(SigarUtil.class);

    private static  CommonConfig commonConfig= (CommonConfig) ApplicationContextHelper.getBean(CommonConfig.class);

    private static  Runtime r = Runtime.getRuntime();
    private static  Sigar sigar = new Sigar();
    private static   OperatingSystem OS = OperatingSystem.getInstance();


    /**
     * 获取内存使用信息
     * @return
     * @throws SigarException
     */
    public static MemState memory() throws SigarException {

        MemState memState = new MemState();
        Mem mem = sigar.getMem();
        long total =  mem.getTotal() / 1024L/ 1024L;
        long used =  mem.getUsed() / 1024L/ 1024L;
        long free =  mem.getFree() / 1024L/ 1024L;
        double usePer = (double)used/(double)total;
        memState.setUsePer(FormatUtil.formatDouble(usePer*100,1));
        memState.setFree(free+"");
        memState.setUsed(used+"");
        memState.setTotal(total+"");
        memState.setHostname(commonConfig.getBindIp());
        return memState;
    }

    /**
     * 获取cpu使用率，等待率，空闲率
     * @return
     * @throws SigarException
     */
    public static CpuState cpu() throws SigarException {
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        double sys = 0;
        double wait = 0;
        double idle = 0;
        for (int i = 0; i < infos.length; i++) {
            CpuInfo info = infos[i];
            sys+=Double.valueOf(FormatUtil.delChar(CpuPerc.format(cpuList[i].getCombined())));
            wait+=Double.valueOf(FormatUtil.delChar(CpuPerc.format(cpuList[i].getWait())));
            idle+=Double.valueOf(FormatUtil.delChar(CpuPerc.format(cpuList[i].getIdle())));
        }
        CpuState cpuState = new CpuState();
        cpuState.setSys(FormatUtil.formatDouble(sys/infos.length,1));
        cpuState.setIdle(FormatUtil.formatDouble(idle/infos.length,1));
        cpuState.setIowait(FormatUtil.formatDouble(wait/infos.length,1));
        cpuState.setHostname(commonConfig.getBindIp());
        return cpuState;
    }


    /**
     * 获取操作系统信息
     * @return
     * @throws SigarException
     */
    public static SystemInfo os() throws SigarException {
        SystemInfo systemInfo = new SystemInfo();
        Sigar sigar = new Sigar();
        CpuInfo infos[] = sigar.getCpuInfoList();
        systemInfo.setHostname(commonConfig.getBindIp());
        systemInfo.setCpuCoreNum(infos.length+"");
        if(infos.length>0){
            systemInfo.setCpuXh(infos[0].getModel());
        }
        systemInfo.setVersion(OS.getVersion());
        systemInfo.setVersionDetail(OS.getDescription()+" "+OS.getArch());
        return systemInfo;
    }


    /**
     * 获取磁盘使用信息
     * @throws Exception
     */
    public static List<DeskState> file(Timestamp t) throws Exception {
        List<DeskState> list = new ArrayList<DeskState>();
        int usedSum = 0;
        int availSum = 0;
        int sizeSum = 0;
        double usePerSum = 0;
        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
            DeskState deskState = new DeskState();
            FileSystem fs = fslist[i];
            deskState.setFileSystem(fs.getDevName());
            deskState.setHostname(commonConfig.getBindIp());
            FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
            usedSum+=(usage.getUsed()/1024/1024);
            deskState.setUsed((usage.getUsed()/1024/1024)+"G");
            availSum+=(usage.getAvail()/1024/1024);
            deskState.setAvail((usage.getAvail()/1024/1024)+"G");
            sizeSum+=(usage.getTotal()/1024/1024);
            deskState.setSize((usage.getTotal()/1024/1024)+"G");
            double usePercent = FormatUtil.formatDouble(usage.getUsePercent()* 100D,2) ;
            usePerSum+=usePercent;
            deskState.setUsePer(usePercent+"%");
            deskState.setCreateTime(t);
            list.add(deskState);
        }
        DeskState deskStateSum = new DeskState();
        deskStateSum.setHostname(commonConfig.getBindIp());
        deskStateSum.setAvail(availSum+"G");
        deskStateSum.setFileSystem("总计");
        deskStateSum.setSize(sizeSum+"G");
        deskStateSum.setUsePer(usePerSum+"%");
        deskStateSum.setUsed(usedSum+"G");
        deskStateSum.setCreateTime(FormatUtil.getDateBefore(t,1));
        list.add(deskStateSum);
        return list;
    }

    /**
     * 获取系统负载
     * @return
     */
    public static SysLoadState getLoadState(SystemInfo systemInfo) throws SigarException {
        SysLoadState sysLoadState = new SysLoadState();
        if(systemInfo==null){
            return null;
        }
        if(systemInfo.getVersionDetail().indexOf("Microsoft")>-1){
            return null;
        }
        double[] load =  sigar.getLoadAverage();
        sysLoadState.setOneLoad(load[0]);
        sysLoadState.setHostname(commonConfig.getBindIp());
        sysLoadState.setFiveLoad(load[1]);
        sysLoadState.setFifteenLoad(load[2]);
        return sysLoadState;
    }

    /**
     * 获取进程信息
     * @return
     */
    public static AppState getLoadPid(String pid) throws SigarException {
        try{
            AppState appState = new AppState();
            ProcCpu ProcCpu = sigar.getProcCpu(pid);
            ProcMem ProcMem = sigar.getProcMem(pid);
            appState.setCpuPer(FormatUtil.formatDouble(ProcCpu.getPercent(),2));
            appState.setMemPer(FormatUtil.formatDouble((ProcMem.getResident()/1024/1024),2));
            return appState;
        }catch (SigarException e){
            logger.error("获取进程信息错误",e);
        }
        return null;
    }


}
