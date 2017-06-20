/**
 * Created by BIG-JIAN on 2017/6/19.
 */
import { Layout, Menu, Breadcrumb, Icon } from 'antd';
import React from 'react';
const { Content, Footer, Sider } = Layout;
const SubMenu = Menu.SubMenu;
import Hello from '../Hello.js';
import Header from  './header.js';
import './layout.css';

export default class SiderDemo extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            collapsed: false,
            mode: 'inline',
        };
    }

    onCollapse(collapsed){
        console.log(collapsed);
        this.setState({
            collapsed,
            mode: collapsed ? 'vertical' : 'inline',
        });
    };

    // style ={{ minHeight: 1000}}

    render() {
        return (
            <Layout className = "ant-layout-base">
                <Sider
                    collapsible
                    collapsed={this.state.collapsed}
                    onCollapse={this.onCollapse}
                >
                    <div className="logo" />
                    <Menu theme="dark" mode={this.state.mode} defaultSelectedKeys={['6']}>
                        <SubMenu
                            key="sub1"
                            title={<span><Icon type="user" /><span className="nav-text">User</span></span>}
                        >
                            <Menu.Item key="1">Tom</Menu.Item>
                            <Menu.Item key="2">Bill</Menu.Item>
                            <Menu.Item key="3">Alex</Menu.Item>
                        </SubMenu>
                        <SubMenu
                            key="sub2"
                            title={<span><Icon type="team" /><span className="nav-text">Team</span></span>}
                        >
                            <Menu.Item key="4">Team 1</Menu.Item>
                            <Menu.Item key="5">Team 2</Menu.Item>
                        </SubMenu>
                        <Menu.Item key="6">
              <span>
                <Icon type="file" />
                <span className="nav-text">File</span>
              </span>
                        </Menu.Item>
                    </Menu>
                </Sider>

                <Layout>
                    <Header style={{ background: '#fff', padding: 0 }} >
                        <div>
                            <h1>xxx</h1>
                        </div>

                    </Header>

                    <header1/>

                    <Content style={{ margin: '0 16px' }}>

                        <Breadcrumb style={{ margin: '12px 0' }}>
                            <Breadcrumb.Item>Userx</Breadcrumb.Item>
                            <Breadcrumb.Item>Bill</Breadcrumb.Item>
                        </Breadcrumb>

                        <div style={{ padding: 24, background: '#fff', minHeight: 550 }}>
                            Bill is a cat.
                            <Hello/>
                        </div>

                    </Content>


                    <Footer style={{ textAlign: 'center' }}>
                        Ant Design ©2016 Created by Ant UED
                    </Footer>

                </Layout>
            </Layout>
        );
    }
}

