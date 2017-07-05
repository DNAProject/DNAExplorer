<template>
  <div class="sidebar_hidden side_fixed">
    <div id="wrapper_all">
      <header id="top_header">
        <div class="container">
          <div class="row">
            <div class="col-xs-6 col-sm-2">
              <!--<a href="dashboard1.html" class="logo_main" title="Ebro Admin Template:"><img width="82" src="../assets/images/lsgy-gray.svg" alt=""></a>-->
              <p id="textup">贵阳身份链</p>
              <p id="textdown">身份链存证平台</p>
            </div>
            <div class="col-sm-push-4 col-sm-3 text-right hidden-xs">
            </div>
          </div>
        </div>
      </header>
      <nav id="top_navigation">
        <div class="container">
          <ul id="icon_nav_h" class="top_ico_nav clearfix">
            <li class="active">
              <a href="#">
                <i class="icon-home icon-2x"></i>
                <span class="menu_label">主页</span>
              </a>
            </li>
            <li>
              <a href="/#/main">
                <i class="icon-edit icon-2x"></i>
                <span class="menu_label">节点</span>
              </a>
            </li>
            <li>
              <a href="/#/main">
                <i class="icon-group icon-2x"></i>
                <span class="menu_label">区块</span>
              </a>
            </li>
            <li>
              <a href="/#/main">
                <i class="icon-tasks icon-2x"></i>
                <span class="menu_label">存证</span>
              </a>
            </li>
            <li>
              <a href="/#/main">
                <i class="icon-beaker icon-2x"></i>
                <span class="menu_label">属性</span>
              </a>
            </li>
            <li>
              <a href="/#/main">
                <i class="icon-book icon-2x"></i>
                <span class="menu_label">常见问题</span>
              </a>
            </li>
            <li>
              <a href="/#/main">
                <i class="icon-tags icon-2x"></i>
                <span class="menu_label">联系管理员</span>
              </a>
            </li>
            <li>
              <a href="/#/main">
                <i class="icon-wrench icon-2x"></i>
                <span class="menu_label">设置</span>
              </a>
            </li>
          </ul>
        </div>
      </nav>
      <nav id="mobile_navigation"></nav>
      <section id="breadcrumbs">
        <div class="container">
          <ul>
            <li><a href="/#/main">监控信息</a></li>
            <li><span>仪表板</span></li>
          </ul>
        </div>
      </section>
      <section class="container clearfix main_section">
        <div id="main_content_outer" class="clearfix">
          <div id="main_content">
            <div class="row">
              <div class="col-lg-3 col-md-6">
                <div class="box_stat box_ico">
                  <span class="stat_ico stat_ico_1"><i class="li_vallet"></i></span>
                  <h4 id="degreeTransactionNum">{{blockStatus.txncount}}</h4>
                  <small>存证数量</small>
                </div>
                <!--<div class="progress" id="progress">-->
                  <!--<div id="progressbar" class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">-->
                  <!--</div>-->
                <!--</div>-->

              </div>
              <div class="col-lg-3 col-md-6">
                <div class="box_stat box_ico">
                  <span class="stat_ico stat_ico_3"><i class="li_banknote"></i></span>
                  <h4 id="degreeHeightNum">{{blockStatus.height}}</h4>
                  <small>区块数量</small>
                </div>
<!--                <div class="progress" id="progress">
                  <div id="progressbar" class="progress-bar progress-bar-warning active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                  </div>
                </div>-->

              </div>
              <div class="col-lg-3 col-md-6">
                <div class="box_stat box_ico">
                  <span class="stat_ico stat_ico_2"><i class="li_user"></i></span>
                  <h4>{{ nodeStatus.correctNum }}</h4>
                  <small>正常节点</small>
                </div>
<!--                <div class="progress" id="progress">
                  <div id="progressbar" class="progress-bar progress-bar-success active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                  </div>
                </div>-->
              </div>
              <div class="col-lg-3 col-md-6">
                <div class="box_stat box_ico">
                  <span class="stat_ico stat_ico_4"><i class="li_truck"></i></span>
                  <h4>{{ nodeStatus.errorNum }}</h4>
                  <small>故障节点</small>
                </div>
<!--                <div class="progress" id="progress">
                  <div id="progressbar" class="progress-bar progress-bar-danger active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                  </div>
                </div>-->
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h4 class="panel-title">位置</h4>
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-sm-7 text-center">
                        <img style="width:500px; max-height:500px" src="../assets/ebro/img/map.jpg" />
                      </div>
                      <div class="col-sm-5">
                        <table class="table table-striped">
                          <thead>
                          <tr>
                            <th>区县</th>
                            <th class="col_small col_center">节点数</th>
                          </tr>
                          </thead>
                          <tbody v-for="">
                          <tr>
                            <td><a href="/#/main">清镇市</a></td>
                            <td class="col_center">2</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">开阳县</a></td>
                            <td class="col_center">2</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">白云县</a></td>
                            <td class="col_center">1</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">乌当区</a></td>
                            <td class="col_center">1</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">观山湖区</a></td>
                            <td class="col_center">1</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">息烽县</a></td>
                            <td class="col_center">0</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">修文县</a></td>
                            <td class="col_center">1</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">云岩县</a></td>
                            <td class="col_center">1</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">南明区</a></td>
                            <td class="col_center">1</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">花溪区</a></td>
                            <td class="col_center">1</td>
                          </tr>
                          <tr>
                            <td><a href="/#/main">其它</a></td>
                            <td class="col_center">2</td>
                          </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-7 hidden">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h4 class="panel-title">存证数量</h4>
                  </div>
                  <div class="panel-body">
                    <div class="chart_wrapper">
                      <div id="flot_social" class="chart_b">
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-sm-5">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h4 class="panel-title">节点类型</h4>
                  </div>
                  <div class="panel-body">
<!--                    <div class="chart_wrapper">-->
                      <!--<div class="chart_b" id="flot_browsers" >-->
                      <div class="text-center" >
                        <img style="width:500px; max-height:500px" src="../assets/ebro/img/node3.jpg" class="img-responsive" /><!--height="250" width="250"-->
                      </div>
                    <!--</div>-->
                  </div>
                  <table class="table table-striped">
                    <thead>
                    <tr>
                      <th>节点类型</th>
                      <th class="col_small col_center">节点数量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                      <td><a href="/#/main">共识节点</a></td>
                      <td class="col_center">6</td>
                    </tr>
                    <tr>
                      <td><a href="/#/main">全节点</a></td>
                      <td class="col_center">4</td>
                    </tr>
                    <tr>
                      <td><a href="/#/main">轻节点</a></td>
                      <td class="col_center">3</td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>

              <div class="col-sm-7">
                <div class="my-table striped">
                  <div class="row title">
                    <div class="col-xs-5">节点名称</div>
                    <div class="col-xs-2">出块状态</div>
                    <div class="col-xs-2">网络状态</div>
                    <div class="col-xs-3">时间</div>
                  </div>
                  <ul id="ul_node" v-for="item in nodeList">
                    <li class="row">
                      <div class="col-xs-5 note-name">{{ item.url }}</div>
                      <div class="col-xs-2 note-state">{{ item.blockStatus }}</div>
                      <div class="col-xs-2 note-net">{{ item.rpcStatus }}</div>
                      <div class="col-xs-3 note-time">{{ item.date }}</div>
                    </li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="row">
              <div class="col-sm-5">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h4 class="panel-title">存证类型</h4>
                  </div>
                  <div class="panel-body">
                    <div class="chart_wrapper" id="node_type">
                      <!--<div class="text-center" >-->
                        <!--<img style="width:500px; max-height:500px" src="../assets/ebro/img/cunzheng.jpg" height="250" width="250"/>-->
                        <canvas id="canvas"  width="280" height="280" >您的浏览器不支持canvas标签</canvas>

                      <div class="showName">
                        <p ><span class="toleft">存证类型</span><span class="toright">数量</span></p>
                        <p ><span class="toleft glyphicon glyphicon-stop one"></span><span class="toleft ">采集存证</span><span class="toright">{{ degreeSum.collectSum }}</span></p>
                        <p ><span class="toleft glyphicon glyphicon-stop two"></span><span class="toleft">确认存证</span><span class="toright">{{ degreeSum.confirmSum }}</span></p>
                        <p ><span class="toleft glyphicon glyphicon-stop three"></span><span class="toleft">审议存证</span><span class="toright">{{ degreeSum.considerSum }}</span></p>
                        <p ><span class="toleft glyphicon glyphicon-stop four"></span><span class="toleft">审议提交存证</span><span class="toright">{{ degreeSum.conlusionSum }}</span></p>
                        <p ><span class="toleft glyphicon glyphicon-stop five"></span><span class="toleft">复议存证</span><span class="toright">{{ degreeSum.reconsiderSum }}</span></p>
                      </div>
                      <!--</div>-->
                    </div>
                  </div>
                  <!--<table class="table table-striped">-->
<!--                  <table class="table striped">
                    <thead>
                    <tr>
                      <th>存证类型</th>
                      <th class="col_small col_center">数量</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr bgcolor="#7BAF41">
                      <td>采集存证</td>
                      <td class="col_center">{{ degreeSum.collectSum }}</td>
                    </tr>
                    <tr bgcolor="#EEA91E">
                      <td>确认存证</td>
                      <td class="col_center">{{ degreeSum.confirmSum }}</td>
                    </tr>
                    <tr bgcolor="#F04B51">
                     <td>审议存证</td>
                      <td class="col_center">{{ degreeSum.considerSum }}</td>
                    </tr>
                    <tr bgcolor="8F4586">
                      <td>审议提交存证</td>
                      <td class="col_center">{{ degreeSum.conlusionSum }}</td>
                    </tr>
                    <tr bgcolor="46A3FF">
                      <td>复议存证</td>
                      <td class="col_center">{{ degreeSum.reconsiderSum }}</td>
                    </tr>
                    </tbody>
                  </table>-->
                </div>
              </div>
              <div class="col-sm-7">
                <div class="my-table striped">
                  <div class="row title">
                    <div class="col-sm-2 col-xs-3">类型</div>
                    <div class="col-sm-3 col-xs-3">存证时间</div>
                    <div class="col-sm-3 col-xs-3">存证编号</div>
                    <div class="col-sm-2 col-xs-3">存证人</div>
                    <div class="col-sm-2 col-xs-3">存证块高度</div>
                  </div>
                  <div id="div_txs">
                    <ul  v-for="item in degreeList">
                      <li id="li_template" class="row">
                        <div class="col-sm-2 col-xs-3 tx-type">{{ item.txType }}</div>
                        <div class="col-sm-3 col-xs-3 tx-time">{{ item.date }}</div>
                        <div class="col-sm-3 col-xs-3">
                          <a class="tx-hash">{{ item.txIdS }}</a>
                        </div>
                        <div class="col-sm-2 col-xs-3 tx-oprNm">{{ item.lastOprNm }}</div>
                        <div class="col-sm-2 col-xs-3 tx-height">{{ item.height }}</div>
                      </li>
                    </ul>
                    <ul id="ul_txs">
                    </ul>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-6">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h4 class="panel-title">存证数量</h4>
                  </div>
                  <div class="chart_wrapper">
                    <div class="chart_wrapper">
                      <div id="flot_overview" class="chart_b">
                       <img style="width:500px; max-height:500px" src="../assets/ebro/img/cunzheng2.jpg" />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-sm-3">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h4 class="panel-title">各角色所做存证</h4>
                  </div>
                  <div class="panel-body">
                    <div class="chart_wrapper">
<!--                      <div class="chart_b" id="flot_browsers3">
                        <img style="width:500px; max-height:500px" src="../assets/ebro/img/role1.jpg" />
                      </div>-->
                      <div class="text-center" >
                        <!--<img style="width:500px; max-height:500px" src="../assets/ebro/img/role11.jpg" height="200" width="220"/>-->
                        <div class="canvas">
                          <canvas id="canvas_two"  width="180" height="180" >您的浏览器不支持canvas标签</canvas>
                        </div>
                        <div class="showName_small">
                          <p ><span class="toleft">名称</span><span class="toright">数量</span></p>
                          <p ><span class="toleft glyphicon glyphicon-stop six"></span><span class="toleft ">被采集人</span><span class="toright">{{ degreeSum.confirmSum+degreeSum.reconsiderSum }}</span></p>
                          <p ><span class="toleft glyphicon glyphicon-stop seven"></span><span class="toleft">采集员</span><span class="toright">{{ degreeSum.collectSum }}</span></p>
                          <p ><span class="toleft glyphicon glyphicon-stop three"></span><span class="toleft">审议员</span><span class="toright">{{ degreeSum.considerSum }}</span></p>
                        </div>

                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-sm-3">
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h4 class="panel-title">当事人确认/未确认存证</h4>
                  </div>
                  <div class="panel-body">
                    <div class="chart_wrapper">
<!--                      <div class="chart_b" id="flot_browsers4">
                        <img style="width:500px; max-height:500px" src="../assets/ebro/img/role2.jpg" />
                      </div>-->
                      <div class="text-center" >
                        <!--<img style="width:500px; max-height:500px" src="../assets/ebro/img/role22.jpg" height="200" width="220"/>-->
                        <div class="canvas">
                          <canvas id="canvas_three"  width="180" height="180" >您的浏览器不支持canvas标签</canvas>
                        </div>
                        <div class="showName_small">
                          <p style="margin-top:25px"><span class="toleft">名称</span><span class="toright">数量</span></p>
                          <p ><span class="toleft glyphicon glyphicon-stop six"></span><span class="toleft ">未确认</span><span class="toright">{{ degreeSumByStatus.unconfirmCzNum }}</span></p>
                          <p ><span class="toleft glyphicon glyphicon-stop seven"></span><span class="toleft">已确认</span><span class="toright">{{ degreeSumByStatus.confirmCzNum }}</span></p>
                        </div>

                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-6">
<!--                <div class="box_stat_circular">
                  <img style="width:500px; max-height:500px" src="../assets/ebro/img/node1.jpg" />
                </div>-->
                <div class="normal_node">
                  <div class="col-xs-3 text-center"><b>正常节点</b></div>
                  <div class="col-xs-4 text-right">
                    <canvas id="canvas_four" width="90px" height="90px"></canvas>
                  </div>
                  <div class="col-xs-5">
                    <p>正常节点占比</p>
                    <p>{{ nodeStatus.correctPer }}%</p>
                  </div>
                </div>

              </div>
              <div class="col-sm-6">
<!--                <div class="box_stat_circular">
                  <img style="width:500px; max-height:500px" src="../assets/ebro/img/node2.jpg" />
                </div>-->
                <div class="fault_node">
                  <div class="col-xs-3 text-center"><b>故障节点</b></div>
                  <div class="col-xs-4 text-right">
                    <canvas id="canvas_five" width="90px" height="90px"></canvas>
                  </div>
                  <div class="col-xs-5">
                    <p>故障节点占比</p>
                    <p>{{ nodeStatus.errorPer }}%</p>
                  </div>
                </div>

              </div>
            </div>
          </div>
        </div>
      </section>
      <div id="footer_space"></div>
    </div>
    <footer id="footer">
      <div class="container">
        <div class="row">
          <div class="col-sm-3">
            © 2017 贵阳身份链
          </div>
          <div class="col-sm-8">
            <ul>
              <li><a>Dashboard</a></li>
              <li>&middot;</li>
              <li><a>Terms of Service</a></li>
              <li>&middot;</li>
              <li><a>Privacy Policy</a></li>
              <li>&middot;</li>
              <li><a>Contact us</a></li>
            </ul>
          </div>
          <div class="col-sm-1 text-right">
            <small class="text-muted">v1.3</small>
          </div>
        </div>
      </div>
    </footer>
    <nav id="side_fixed_nav">
      <div class="slim_scroll">
        <div class="side_nav_actions">
          <a href="javascript:void(0)" id="side_fixed_nav_toggle"><span class="icon-align-justify"></span></a>
          <div id="toogle_nav_visible" class="make-switch switch-mini" data-on="success" data-on-label="<i class='icon-lock'></i>" data-off-label="<i class='icon-unlock-alt'></i>">
            <input id="nav_visible_input" type="checkbox">
          </div>
        </div>
        <ul id="text_nav_side_fixed">
          <li>
            <a href="javascript:void(0)"><span class="icon-dashboard"></span>Dashboard</a>
            <ul>
              <li class="link_active"><a href="dashboard.html">Dashboard</a></li>
              <li><a href="dashboard_drag_drop.html">Drag & Drop Dashboard</a></li>
              <li>
                <a href="javascript:void(0)">Navigations</a>
                <ul>
                  <li><a href="nav_side_accordion.html">Accordion Navigation</a></li>
                  <li><a href="nav_side_icons.html">Icon Navigation</a></li>
                </ul>
              </li>
            </ul>
          </li>
          <li>
            <a href="javascript:void(0)"><span class="icon-th-list"></span>Forms</a>
            <ul>
              <li><a href="form_regular_elements.html">Regular elements</a></li>
              <li><a href="form_extended_elements.html">Extended elements</a></li>
              <li><a href="form_multiupload.html">Multiupload</a></li>
              <li><a href="form_validation.html">Form validation</a></li>
              <li><a href="wizard.html">Wizard</a></li>
              <li><a href="wysiwg.html">WYSIWG Editor</a></li>
            </ul>
          </li>
          <li>
            <a href="javascript:void(0)"><span class="icon-puzzle-piece"></span>Components</a>
            <ul>
              <li><a href="calendar.html">Calendar</a></li>
              <li><a href="charts.html">Charts</a></li>
              <li><a href="contact_list.html">Contact List</a></li>
              <li><a href="editable_elements.html">Editable Elements</a></li>
              <li><a href="file_manager.html">File manager</a></li>
              <li><a href="gallery.html">Gallery</a></li>
              <li><a href="gmaps.html">Google Maps</a></li>
              <li>
                <a href="javascript:void(0)">Tables</a>
                <ul>
                  <li><a href="datatables.html">Datatables</a></li>
                  <li><a href="regular_tables.html">Regular</a></li>
                  <li><a href="slick_grid.html">Slick Grid</a></li>
                  <li><a href="table_responsive.html">Responsive Table</a></li>
                </ul>
              </li>
              <li><a href="tree_plugin.html">Tree Plugin</a></li>
            </ul>
          </li>
          <li>
            <a href="javascript:void(0)"><span class="icon-beaker"></span>UI Elements</a>
            <ul>
              <li><a href="alerts_buttons.html">Alerts, Buttons</a></li>
              <li><a href="grid.html">Grid</a></li>
              <li><a href="icons.html">Icons</a></li>
              <li><a href="notifications.html">Notifications</a></li>
              <li><a href="panels.html">Panels</a></li>
              <li><a href="tabs_accordions.html">Tabs, Accordions</a></li>
              <li><a href="tooltips_popovers.html">Tooltips, Popovers</a></li>
              <li><a href="typography.html">Typography</a></li>
            </ul>
          </li>
          <li>
            <a href="javascript:void(0)"><span class="icon-leaf"></span>Other Pages</a>
            <ul>
              <li><a href="blank.html">Blank page</a></li>
              <li><a href="chat.html">Chat</a></li>
              <li><a href="contact_page.html">Contact Page</a></li>
              <li><a href="error_404.html">Error 404</a></li>
              <li><a href="help_faq.html">Help/Faq</a></li>
              <li><a href="invoices.html">Invoices</a></li>
              <li><a href="landing_page.html">Landing Page</a></li>
              <li><a href="login_page.html">Login Page</a></li>
              <li><a href="mailbox.html">Mailbox</a></li>
              <li><a href="pricing_table.html">Pricing Table</a></li>
              <li><a href="search_page.html">Search Page</a></li>
              <li><a href="settings.html">Site Settings</a></li>
              <li><a href="user_profile.html">User profile</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>
<script>
  import '../assets/ebro/bootstrap/css/bootstrap.min.css'
  import '../assets/ebro/css/font-awesome/css/font-awesome.min.css'
  import { mapGetters } from 'vuex'
  export default {

    methods:{
      drawCircle:function(x,y,r,color, beginPoint, radian, circlewide){
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.beginPath();
        ctx.strokeStyle = color;
        ctx.arc(x, y, r, beginPoint, radian, false);
        ctx.lineWidth = circlewide;
        //	ctx.lineCap="round";
        ctx.stroke();
        ctx.fillStyle="white";
        ctx.font=20;
/*        ctx.fillText("审议存证",x+80,y-10);
        ctx.fillText("采集存证",x+80,y+20);
        ctx.fillText("确认存证",x-80,y-80);*/
        ctx.closePath();
      },
      drawCircle_two:function(x,y,r,color, beginPoint, radian, circlewide){
        var canvas_two = document.getElementById("canvas_two");
        var ctx = canvas_two.getContext("2d");
        ctx.beginPath();
        ctx.strokeStyle = color;
        ctx.arc(x, y, r, beginPoint, radian, false);
        ctx.lineWidth = circlewide;
        //	ctx.lineCap="round";
        ctx.stroke();
        ctx.closePath();
      },
      drawCircle_three:function(x,y,r,color, beginPoint, radian, circlewide){
        var canvas_three = document.getElementById("canvas_three");
        var ctx = canvas_three.getContext("2d");
        ctx.beginPath();
        ctx.strokeStyle = color;
        ctx.arc(x, y, r, beginPoint, radian, false);
        ctx.lineWidth = circlewide;
        //	ctx.lineCap="round";
        ctx.stroke();
        ctx.closePath();
      },
      drawCircle_four:function(x,y,r,color, beginPoint, radian, circlewide,num){
        var canvas_four = document.getElementById("canvas_four");
        var ctx = canvas_four.getContext("2d");
        ctx.beginPath();
        ctx.fillStyle="white";
        ctx.arc(45,45,40,0,2*Math.PI,true);
        ctx.fill();
        ctx.font="16px Courier New";
        ctx.fillStyle = "gray";
        ctx.fillText( num+"个",30,49);
        ctx.closePath();
        ctx.beginPath();
        ctx.strokeStyle = color;
        ctx.arc(x, y, r, beginPoint, radian, false);
        ctx.lineWidth = circlewide;
        ctx.lineCap="round";
        ctx.stroke();
        ctx.closePath();
      },
      drawCircle_five:function(x,y,r,color, beginPoint, radian, circlewide,num){
        var canvas_five = document.getElementById("canvas_five");
        var ctx = canvas_five.getContext("2d");
        ctx.beginPath();
        ctx.fillStyle="white";
        ctx.arc(45,45,40,0,2*Math.PI,true);
        ctx.fill();
        ctx.font="16px Courier New";
        ctx.fillStyle = "gray";
        ctx.fillText(num+"个",41,49);
        ctx.textAlign="center";
        ctx.closePath();
        ctx.beginPath();
        ctx.strokeStyle = color;
        ctx.arc(x, y, r, beginPoint, radian, false);
        ctx.lineWidth = circlewide;
        ctx.lineCap="round";
        ctx.stroke();
        ctx.closePath();
      }

    },
    created () {
      this.$store.dispatch('degreeList')
    /*  this.$store.dispatch('blockList')*/
      this.$store.dispatch('nodeList')
      this.$store.dispatch('nodeStatus')
      this.$store.dispatch('blockStatus')
      this.$store.dispatch('degreeSum')
      this.$store.dispatch('degreeSumByStatus')
      this.intervalBlock = setInterval(() => {
        this.$store.dispatch('blockStatus')
        this.$store.dispatch('nodeStatus')
        this.$store.dispatch('degreeList')
 /*       this.$store.dispatch('blockList')*/
        this.$store.dispatch('nodeList')
        this.$store.dispatch('degreeSum')
        this.$store.dispatch('degreeSumByStatus')
      }, 2000)

    },

   watch: {
      'degreeSum': function(val, oldVal) {
        var total = val.collectSum +val.confirmSum +val.considerSum +val.reconsiderSum +val.conlusionSum
        this.drawCircle(130,130,80,"#7BAF41",0, Math.PI * (val.collectSum/total) * 2,60)
        this.drawCircle(130,130,80,"#EEA91E", Math.PI * (val.collectSum/total) * 2, Math.PI * ((val.collectSum+val.confirmSum)/total) * 2,60)
        this.drawCircle(130,130,80,"#F04B51", Math.PI * ((val.collectSum+val.confirmSum)/total) * 2, Math.PI * ((val.collectSum+val.confirmSum+val.considerSum)/total) *  2,60)
        this.drawCircle(130,130,80,"#46A3FF", Math.PI * ((val.collectSum+val.confirmSum+val.considerSum)/total) * 2, Math.PI * ((val.collectSum+val.confirmSum+val.considerSum+val.reconsiderSum)/total) * 2,60)
        this.drawCircle(130,130,80,"#8F4586", Math.PI * ((val.collectSum+val.confirmSum+val.considerSum+val.reconsiderSum)/total) * 2, Math.PI *  2,60)

        //各角色所做存证
        var total2 = val.collectSum + val.confirmSum + val.considerSum + val.reconsiderSum
        this.drawCircle_two(80,80,60,"#B1B1B1",0,Math.PI*2*((val.confirmSum+val.reconsiderSum)/total2),40);
        this.drawCircle_two(80,80,60,"#0792CD",Math.PI*2*((val.confirmSum+val.reconsiderSum)/total2),Math.PI*((val.confirmSum+val.reconsiderSum+val.collectSum)/total2)*2,40);
        this.drawCircle_two(80,80,60,"#F04B51",Math.PI*((val.confirmSum+val.reconsiderSum+val.collectSum)/total2)*2,Math.PI*2,40);

      },
      'nodeStatus':function (val, oldVal) {
        //正常节点
        this.drawCircle_four(45, 45, 35, "#7BAF41", Math.PI * 0, Math.PI * ( val.correctNum / val.totalCount ) * 2, 4, val.correctNum);
        //故障节点
        this.drawCircle_five(45, 45, 35, "#F04B51 ", Math.PI * ( val.correctNum / val.totalCount ) * 2, Math.PI * 2, 4, val.errorNum);
      },
      'degreeSumByStatus':function (val, oldVal) {
        var total = val.confirmCzNum + val.unconfirmCzNum
        //当事人确认存证
        this.drawCircle_three(80,80,60,"#0792CD",0,Math.PI*2*(val.confirmCzNum/total),40);
        ///当事人未确认存证
        this.drawCircle_three(80,80,60,"#B1B1B1",Math.PI*2*(val.confirmCzNum/total),Math.PI*2,40);
      }

  },

    beforeDestroy () {
      clearInterval(this.intervalBlock)
    },
    computed: mapGetters({
      degreeList: 'degreeList',
/*      blockList: 'blockList',*/
      nodeList: 'nodeList',
      nodeStatus: 'nodeStatus',
      blockStatus: 'blockStatus',
      degreeSum: 'degreeSum',
      degreeSumByStatus: 'degreeSumByStatus'
    }),
    data () {
      return {
      }
    }
  }
</script>

<style>
  #node_type{
    position: relative;
  }
  .showName,.showName_small{
    /*position: absolute;*/
    float: right;
    bottom: 0;
    right: 0;
  }

  .showName>p{
    overflow: hidden;
    width: 150px;
    vertical-align: middle;
    font-size: 13px;
    line-height: 13px;
  }
  .showName_small>p{
    overflow: hidden;
    width: 100px;
    vertical-align: middle;
    font-size: 13px;
    line-height: 13px;
  }
  .one{
    color: #7BAF41;/*绿色*/
  }
  .two{
    color: #EEA91E;/*橘黄色*/
  }
  .three{
    color:#F04B51 ;/*红色*/
  }
  .four{
    color: #8F4586;/*浅蓝*/
  }
  .five{
    color:#46A3FF ;/*紫色*/
  }
  .six{
    color:#B1B1B1 ;/*灰色*/
  }
  .seven{
    color:#0792CD ;/*深蓝*/
  }
  .showName>p>span,.showName_small>p>span{
    display: inlin-block;
  }

  .toleft{
    float: left;
  }
  .toright{
    float: right;
  }
  @media only screen and (min-width: 1200px) {
    .showName{
      margin-top: -120px;
    }
    .showName_small{
      margin-top: -20px;
      margin-right: -10px;
    }
  }
  @media only screen and (max-width:768px ) {
    .showName{
      margin-top: -220px;
      margin-right: 50px;
    }
    .showName_small{
      margin-top: -130px;
      margin-right:150px ;
    }

  }
  @media only screen and (max-width:550px ) {
    .canvas{
      margin-left:-20px;
    }
    .showName,.showName_small{
      margin-right: 0;
    }
  }
  @media only screen and (max-width:470px ) {
    .showName{
      margin-top: 0;
    }
  }

  #textup,#textdown{
     margin:0;
     padding: 0;

   }
  #textup{
    font-size: 16px;
    color: #707070;
    font-weight: bold;
    letter-spacing: 3px;
  }
  #textdown{
    font-size: 5px;
    color: #A9A9A9;
    letter-spacing: 1px;
  }

  #progress{
    border-radius: 0px;
    height: 10px;
  }

  .normal_node,.fault_node{
    height: 90px;
    display: flex;
    justify-content:center;
    align-items:Center;
    background:-moz-linear-gradient(left,#EEEEEE,#F7F7F7);
    background: -ms-linear-gradient(left,#EEEEEE,#F7F7F7);
    background: -webkit-linear-gradient(left,#EEEEEE,#F7F7F7);
    background:linear-gradient(to right,#EEEEEE,#F7F7F7) ;
  }


  @keyframes tolong
  {
    from {width: 0;}
    to {width: 100%;}
  }

  @-moz-keyframes tolong /* Firefox */
  {
    from {width: 0;}
    to {width: 100%;}
  }

  @-webkit-keyframes tolong /* Safari 和 Chrome */
  {
    from {width: 0;}
    to {width: 100%;}
  }

  @-o-keyframes tolong /* Opera */
  {
    from {width: 0;}
    to {width: 100%;}
  }

  #progressbar
  {
    animation: tolong 2s;
    -moz-animation:tolong 2s;	/* Firefox */
    -webkit-animation: tolong 2s;	/* Safari 和 Chrome */
    -o-animation: tolong 2s;	/* Opera */
    animation-iteration-count: infinite;
    -moz-animation-iteration-count: infinite;
    -webkit-animation-iteration-count: infinite;
    -o-animation-iteration-count: infinite;
  }


</style>
