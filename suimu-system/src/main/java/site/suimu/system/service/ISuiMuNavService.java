package site.suimu.system.service;

import site.suimu.system.domain.NavClassify;

import java.util.List;

public interface ISuiMuNavService {


    List<NavClassify> tree(NavClassify navClassify);

}
