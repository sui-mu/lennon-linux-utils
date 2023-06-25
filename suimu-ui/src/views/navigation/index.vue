<template>
    <div class="app-wrapper">
        <!-- 唯美的导航名称：繁星引    繁星点点，指引前行方向。 -->
        <div class="navigation_sider" v-title="title">
            <div class="navigation_sider_title" @click="handleTitleClick">
                <h1 style="text-align:center">易导航</h1>
            </div>

            <div class="navigation_sider_menu">
                <el-divider style="color:white"></el-divider>
                <div v-for=" top  in  tabs " :key="top.id">
                    <li><i class="el-icon-sugar"></i><a :href="'#' + top.id">{{ top.label }}</a></li>
                </div>
            </div>
        </div>
        <div class="navigation_main">
            <div class="navigation_header">
                <!-- 智能化开发导航 -->
            </div>
            <div class="navigation_body">
                <div class="navigation_box" v-for=" top  in  tabs " :key="top.id">
                    <a href="#" :name="top.id"></a>
                    <el-card>
                        <div class="card_header">
                            {{ top.label }}
                        </div>
                        <div class="card_body">
                            <!-- 没有子菜单 -->
                            <div class="notabs_body" v-show="top.navLinks.length" style="min-height: 200px;">
                                <div class="divider"></div>
                                <div v-for=" navlink  in  top.navLinks " :key="navlink.id">
                                    <link-item :linkItem="navlink" />
                                </div>
                                <!-- <div v-for="navlink in top.navLinks" :key="navlink.id">
                  <a target="_blank" :href="navlink.linkUrl">
                    <div class="item">
                      <el-row>
                        <div class="logo">
                          <el-col :span="8">
                            <el-image :src="navlink.icon" class="logo-img" />
                          </el-col>
                          <el-col :span="8">
                            <div>{{navlink.name}}</div>
                          </el-col>
                          <el-col :span="8">
                            <el-tooltip placement="top">
                              <div slot="content">多行信息<br />第二行信息</div>
                              <el-button>Top center</el-button>
                            </el-tooltip>
                          </el-col>
                        </div>
                      </el-row>
                      <el-row>
                        <div class="desc">{{navlink.intro}}</div>
                      </el-row>
                    </div>
                  </a>
                </div> -->
                            </div>
                            <!-- 有子菜单 -->
                            <el-tabs class="tabs_body" v-show="!top.navLinks.length" style="min-height: 200px;">
                                <el-tab-pane class="panel_body" v-for=" classify  in  top.children " :key="classify.id"
                                    :label="classify.label">
                                    <div v-for=" navlink  in  classify.navLinks " :key="navlink.id">
                                        <link-item :linkItem="navlink" />
                                    </div>
                                </el-tab-pane>
                            </el-tabs>
                        </div>
                    </el-card>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import LinkItem from './linkItem'
import { classifyTree } from '@/api/system/navigation/nav.js'
export default {
    name: "Index",
    components: {
        LinkItem
    },
    data() {
        return {
            // 版本号
            version: "3.7.0",
            title: "易导航",
            tabs: [],
            querys: {}
        };
    },
    methods: {
        handleTitleClick() {
            window.location = "/index";
        },
        initData() {
            console.log(this.querys);
            classifyTree(this.querys).then(response => {
                this.tabs = response.data;
            })
        },
        getUrl() {
            var url = window.location.search;
            //判断是否有参数？
            if (url.indexOf("?") != -1) {
                //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
                var str = url.substr(1);
                //用等号进行分隔 （只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
                let strs = str.split("=");
                this.querys.userKey = strs[1];
            }
            this.initData();
        }
    },
    mounted() {
        this.getUrl()

    }
};
</script>

<style scoped lang="scss">
@import "~@/assets/styles/mixin.scss";
@import "~@/assets/styles/variables.scss";

.divider {
    width: 100%;
    height: 2px;
    background-color: #dfe4ed;
    margin-bottom: 2%;
}

.navigation_sider {
    position: fixed;
    width: #{$base-sidebar-width};
    color: white;
    background-color: #4d4d4d;

    .navigation_sider_title {
        height: 60px;
    }

    .navigation_sider_menu {
        top: 60px;
        color: white;
        position: fixed;
        padding: 20px;
        margin-top: 10px;
        width: #{$base-sidebar-width};
        border-radius: 15px;
        height: 100%;
        background-color: #4d4d4d;
    }
}

.navigation_main {
    min-height: 100%;
    transition: margin-left 0.28s;
    margin-left: $base-sidebar-width;
    position: relative;
    background-color: #d9d9d9;
    min-height: 1080px;
}

.navigation_header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$base-sidebar-width});
    transition: width 0.28s;
}

.navigation_body {
    position: relative;
}

.navigation_box {
    margin: 0%;
    padding: 1% 1%;
}

.notabs_body {
    margin: 1% 1%;
}

.tabs_body {
    margin: 0% 1%;
}

.panel_body {
    padding: 1% 0%;
}

.card_header {
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
        "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    font-size: 20px;
    font-weight: bold;
    padding: 0% 0% 0% 1%;
}

.card_body {
    padding: 0.5% 0%;
}
</style>

