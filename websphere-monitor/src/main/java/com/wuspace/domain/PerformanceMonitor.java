package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WUCHAO on 2017/3/11.
 */
@Getter
@Setter
public class PerformanceMonitor {

    private List<WebsphereNode> websphereNodes = new ArrayList<WebsphereNode>();
}
