# docker学习笔记

## 基础知识
    
### docker三要素 镜像、容器、仓库

## docker安装

### 1. 前提条件
    CentOS 7 要求系统64位、系统内核版本为3.10以上
    CentOS 6.5以上 要求系统64位、系统内核版本为2.6.32-431或者更高版本
### 2. CentOS 6.8 安装Docker

### 3. CentOS 7 安装Docker
* 官网安装手册
https://docs.docker.com/engine/install/centos/
* 确定是CentOs7版本以上
    * cat /etc/redhat-release
    * CentOS Linux release 7.8.2003 (Core)
* yum安装gcc相关
    * CentOS7能上外网 ping www.baidu.com 可以ping通
    * yum -y install gcc
    * yum -y install gcc-c++
* 卸载旧版本
      sudo yum remove docker \
                      docker-client \
                      docker-client-latest \
                      docker-common \
                      docker-latest \
                      docker-latest-logrotate \
                      docker-logrotate \
                      docker-engine

* 安装需要的软件包
    * sudo yum install -y yum-utils device-mapper-persistent-data lvm2

* 设置stable镜像仓库
    * 国外连接: sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
    * 阿里云连接: sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

* 更新yum软件包索引
    * sudo yum makecache fast

* 安装Docker ce
    * sudo yum -y install docker-ce
* 安装指定版本的Docker ce
    * yum list docker-ce.x86_64 --showduplicates | sort -r
    * sudo yum -y install docker-ce-[VERSION]
* 启动docker
    * sudo service docker start
* 测试
    * docker version
    * docker run hello-world

* 配置镜像加速
    * mkdir -p /etc/docker
    * vi /etc/docker/daemon.json
    * 自己账号的加速地址json数据
    * sudo systemctl daemon-reload
    * sudo systemctl restart docker
* 卸载 
    * systemctl stop docker
    * yum -y remove docker-ce
    * rm -rf /var/lib/docker      
   
### 4. 查看内核版本
    uname -r
    3.10.0-327.el7.x86_64
### 5. 查看CentOS版本
    cat /etc/redhat-release
    CentOS Linux release 7.8.2003 (Core)

### 6. 配置阿里云加速服务器
    阿里云镜像:https://promotion.aliyun.com/ntms/act/kubernetes.html
                
    CentOs7以下: vi /etc/sysconfig/docker
           other_args="--registry-mirror=https://自己账号的加速地址"
    重启docker服务
            service docker restart
            
            ps -ef | grep docker      
            
    CentOs7++
        sudo mkdir -p /etc/docker
        sudo tee /etc/docker/daemon.json <<-'EOF'
        {
          "registry-mirrors": ["https://自己账号的加速地址"]
        }
        EOF
        sudo systemctl daemon-reload
        sudo systemctl restart docker        
              
## docker常用命令

### 帮助命令
    1. docker version 查看docker的版本
    2. docker info    查看docker的详细信息
    3. docker help    查看docker的帮助命令 
### 镜像命令
    1. docker images 列出本地主机上的镜像
      参数说明: REPOSITORY:表示镜像的仓库源
               TAG:       镜像的标签
               IMAGE ID:镜像id
               CREATED:镜像创建时间
               SIZE:镜像大小
               
      OPTIONS说明:-a 列出本地所有的镜像(含中间映像层)
                  -q 只显示镜像id
                  -qa 显示当前镜像的所有镜像id
                  --digests 显示镜像的摘要信息
                  --no-trunc 显示完整的镜像信息(完整的镜像id)
                           
    2. docker serach 某个镜像名字 
        网站: https://hub.docker.com
        OPTIONS说明: --no-trunc 显示完整的镜像信息
                     -s: 列出收藏数不小于指定值的镜像
                     --automated:只列出 automated build类型的镜像
       docker pull tomcat:8.5-jdk8-adoptopenjdk-openj9
                      
    3. docker pull 镜像名字 等价于docker pull 镜像名字:latest
        docker pull tomcat:8.5-jdk8-adoptopenjdk-openj9
    4. docker rmi -f hello-world(默认强制删除版本为latest)
        建议删除的时候指定版本号: docker rmi -f hello-world:latest
      删除多个镜像:docker rmi -f 镜像1 镜像2
      删除所有镜像文件:docker rmi -f $(docker images -qa)  

### 容器命令 有镜像才能创建容器

#### 新建并启动容器
    docker run [OPTIONS] IMAGE [ARG...]
    
    OPTIONS说明:
        --name="容器新名字": 为容器指定一个名称:
        -d: 后台运行容器,并返回容器ID,即启动守护式容器
        -i: 以交互模式运行容器,通常与-t同时使用
        -t: 为容器重新分配一个伪输入终端,通常与-i同时使用
        -P: 随机端口映射
        -p: 指定端口映射,有以下四种格式
            ip:hostPort:containerPort
            ip::containerPort
            hostPort:containerPort
            containerPort
    
    启动交互式容器:
        1. docker run -it 0d120b6ccaa8(IMAGE ID)   启动容器, 容器名字是随机的
        2. docker run -it --name mycentos01 centos 指定容器名字并启动
    
#### 列出当前所有正在运行的容器
    docker ps [OPTIONS]
    
    OPTIONS说明:
        -a: 列出当前所有正在运行的容器+历史上运行过的
        -l: 显示最近创建的容器
        -n: 显示最近n个创建的容器
        -q: 静默模式,值显示容器编号
        --no-trunc:不截断输出
    docker ps 显示当前正在运行的容器
    
#### 退出容器
    1. exit 容器停止退出
    2. ctrl+p+q 容器不停止退出

#### 启动容器
    docker start 容器ID或者容器名

#### 重启容器
    docker restart 容器ID或者容器名

#### 停止容器
    docker stop 容器ID或者容器名

#### 强制停止容器
    docker kill 容器ID或者容器名

#### 删除已停止的容器
    docker rm 容器ID 
    一次性删除多个容器:
        docker rm -f $(docker ps -a -q)
        docker ps -a -q | xargs docker rm

#### **重要**
##### 启动守护式容器
    docker run -d 容器名
    
    问题:docker ps -a 进行查看,会发现容器已经退出
    原因:docker 容器后台运行,就必须有一个前台进程,容器运行的命令如果不是一直挂起的命令(比如运行top,tail),就是会自动退出的
        这个是docker的机制问题,比如你的web容器,我们以nginx为例,正常情况下,我们配置启动服务只需要启动响应的service即可。
        例如: service nginx start
        但是这样做,nginx为后台进程模式运行,就导致docker前台没有运行的应用,
        这样的容器后台启动后,会立即自杀因为他觉得他没事可做了。
        所以,最佳的解决方案是,将你要运行的程序以前台进程的形式运行
    

##### 查看容器日志
    docker logs -f -t --tail 3 容器ID
    
    -t是加入时间戳
    -f跟随最新的日志打印
    --tail数字 显示最多多少条

##### 查看容器内运行的进程
    docker top 容器ID

##### 查看容器内部细节
    docker inspect 容器ID

##### 进入正在运行的容器并以命令行交互
    docker exec -it 容器ID bashShell
    重新进入 docker attach 容器ID
    
    上面两个区别: attch 直接进入容器启动命令的终端,不会启动新的进程
                 exec 是在容器中打开新的终端,并且可以启动新的进程

##### 从容器内拷贝文件到主机上
    docker cp 容器ID:容器内路径 目的主机路径   

### 常用命令
      attach      当前shell下attach连接指定运行镜像
      build       通过Dockerfile构建映像
      commit      提交当前容器为新的镜像
      cp          从容器中拷贝指定文件或者目录到宿主机上
      create      创建一个新的容器,同run,但不启动容器
      diff        查看docker容器变化
      events      从docker服务获取容器实时事件
      exec        在已存在的容器上运行命令
      export      导出容器的内容流作为一个tar归档文件[对应import]
      history     展示一个镜像形成历史
      images      列出系统当前镜像
      import      从tar包中的内容创建一个新的文件系统影响[对应export]
      info        显示系统相关信息
      inspect     查看容器详细信息
      kill        kill 指定docker容器
      load        从一个tar包中加载一个镜像[对应save]
      login       注册或者登录一个docker源服务器
      logout      从当前Docker registry退出
      logs        输出当前容器日志信息
      pause       暂停容器
      port        查看映射端口对应的容器内部源端口
      ps          列出容器列表
      pull        从docker镜像源服务器拉去指定镜像或者库镜像
      push        推送指定镜像或者库镜像至docker源服务器
      rename      重命名一个容器
      restart     重启运行的容器
      rm          移除一个或者多个容器
      rmi         移除一个或多个镜像[无容器使用该镜像才可删除,否则需删除相关容器才可继续或-f强制删除]
      run         创建一个新的容器并运行一个命令
      save        保存一个镜像为一个tar包[对应load]
      search      在docker hub 中搜索镜像
      start       启动容器
      stats       显示容器资源使用统计数据的实时流
      stop        停止容器
      tag         给源中镜像打标签
      top         显示容器正在运行的进程
      unpause     取消暂停容器
      update      更新一个或多个容器的配置
      version     显示Docker版本信息
      wait        截取容器停止时的退出状态值

          

## docker镜像

### 是什么
    镜像是一种轻量级、可执行的独立软件包,用来打包软件运行环境和基于运行环境开发的软件,它包含运行某个软件所需的所有内容,
    包括代码、运行时、库、环境变量和配置文件

#### UnionFs(联合文件系统)
    Union文件系统是一种分层、轻量级并且高性能的文件系统,它支持对文件系统的修改作为一次提交来一层层的叠加,同时可以将不同目录挂载到同一个虚拟文件系统下。
    Union文件系统是Docker镜像的基础。
    镜像可以通过分层来进行继承,基于基础镜像(没有父镜像),可以制作各种具体的应用镜像。
    
    特性: 一次同时加载多个文件系统,但从外面看起来,只能看到一个文件系统,联合加载会把各层文件系统叠加起来,这样最终的文件系统会包含所有底层的文件和目录
#### Docker镜像加载原理(为什么小,因为公用内核)
    docker的镜像实际上由一层一层的文件系统组成,这种层级的文件系统UnionFs。
    bootfs(boot file system)主要包含bootloader和kernel,bootloader主要是引导加载kernel,Linux刚启动时会加载bootfs文件系统,
    在docker镜像的最底层是bootfs.这一层与我们典型的Linux/Unix系统是一样的,包含boot加载器和内核。
    当boot加载完成之后整个内核就都在内存中了,此时内存的使用权已由bootfs转交给内行业,此时系统也会卸载bootfs.
    
    rootfs(root file system),在bootfs之上。包含的就是典型Linux系统中的/dev,/proc,/bin,/etc 等标准目录和文件。rootfs就是各种
    不同的操作系统发行版,比如Ubuntu,Centos等等。
    
    对于一个精简的OS,rootfs可以很小,只需要包括最基本的命令、工具和程序库就可以了,因为底层直接用Host的kernel,自己只需要提供rootfs就行了。
    镜像的bootfs基本上是一致的,rootfs会有差别,一次不同的发行版可以公用bootfs.

#### Docker分层结构
    优点:共享资源
    比如:有多个镜像都从相同的base镜像构建而来,那么宿主机只需在磁盘上保存一份base镜像,
         同时内存中也只需加载一份base镜像,就可以为所有容器服务了。而且镜像的每一层都可以被共享

### 特点
    Docker镜像都是只读的 
    当容器启动时, 一个新的可写层被加载到镜像的顶部。
    这一层通常被曾作"容器层", "容器层"之下的都叫"镜像层"。

### Docker镜像commit操作补充
    docker commit提交容器副本使之成为一个新的的镜像
    
    docker commit -m="提交的描述信息" -a="作者" 容器ID 要创建的目标镜像名:[标签名]

#### 案例演示
    1. 从Hub上下载tomcat镜像到本地并成功运行
        docker run -it -p 8888:8080 --name tomcat tomcat
        -p 主机端口:docker容器端口
        -P 随机分配端口
         i 交互
         t 终端
        注意:如果访问 8888端口报404
        执行命令:docker exec -it 容器ID /bin/bash
                rm -rf webapps
                mv webapps.dist webapps
                最后重启tomcat: docker restart 容器ID
         
             
    2. 故意删除上一步镜像生产tomcat容器的文档
        docker exec -it 容器ID /bin/bash
        rm -rf webapps/docs
        
    3. 也即当前的tomcat运行实例是一个没有文档内容的容器,
      以它为模板commit一个没有doc的tomcat新镜像
        docker commit -m='tomcat without docs' -a='echo' 容器ID echo/mytomcat:1.0.0
      
    4. 启动新镜像
        docker run -it --name mytomcat -p 9980:8080  echo/mytomcat:1.0.0
      
      访问tomcat地址 http://192.168.56.161:9980/docs/ 404 已删除            
    

## Docker容器数据卷

### 是什么
    Docker理念:
        1. 将运用与运行的环境打包形成容器运行,运行可以伴随着容器,但是我们对数据的要求希望是持久化的
        2. 容器之间希望有可能共享数据
    
    Docker容器产生的数据,如果不通过docker commit生成新的镜像,使得数据作为镜像的一部分保存下来,那么当容器删除后,数据自然也没有了。
    
    为了能保存数据在docker中我们使用卷    

### 能干嘛

#### 容器持久化
    卷就是目录或文件,存在于一个或多个容器中,由docker挂载到容器,但不属于联合文件系统,因此能够绕过Union File System提供
    一些用于持续存储或共享数据的特性:
    
        卷的设计目的就是数据的持久化,完全独立于容器的生存周期,因此Docker不会在容器删除时删除其挂载的数据卷
        
    特点:
        1. 数据卷可在容器之间共享或重用数据
        2. 卷中的更改可以直接生效
        3. 数据卷中的更改不会包含在镜像的更新中
        4. 数据卷的生命周期一直持续到没有容器使用它为止
#### 容器间继承+共享数据            

### 数据卷
    容器内添加
    
#### 直接命令添加
    docker run -it -v  /宿主机绝对路径目录:/容器内目录 镜像名  
    docker run -it -v /myDataVolume:/dataVolumeContainer 镜像名
    
    查看数据卷是否挂载成功:
        docker inspect 容器ID
        "HostConfig": {
                    "Binds": [
                        "/myDataVolume:/dataVolumeContainer"
                    ],
        "Mounts": [
                    {
                        "Type": "bind",
                        "Source": "/myDataVolume",
                        "Destination": "/dataVolumeContainer",
                        "Mode": "",
                        "RW": true,
                        "Propagation": "rprivate"
                    }
                ],            
        如果有以上上面信息,则为挂载成功
    
    容器和宿主机之间数据共享
    
    容器停止退出后,主机修改后数据同步
    
    命令(带权限 只读不可写): docker run -it -v /宿主机绝对路径目录:/容器内目录:ro 镜像名                   
#### DockerFile添加
    根目录下新建myDocker文件夹并进入 mkdir myDocker
    
    可在DockerFile中使用VOLUME指令来给镜像添加一个或多个数据卷
        VOLUME["/dataVolumeContainer","/dataVolumeContainer2","/dataVolumeContainer3"]
        说明:
            出于可一直和分享的考虑,用-v主机目录:容器目录这种方法不能够直接在Dockerfile中实现.
            由于宿主机目录是依赖于特定宿主机的,并不能够保证在所有的宿主机上都存在这样的特定目录.
            
    File构建: cd myDocker    vi dockerFile
    
```dockerfile
# volume test
FROM centos
VOLUME ["/dataVolumeContainer1","/dataVolumeContainer2"]
CMD echo "finished,-----------success1"
CMD /bin/bash
```    
    
    build后生成镜像
        docker build -f /myDocker/dockerFile -t echo/centos . (一定要加. 不然会报错)
        获得一个新镜像 echo/centos
    
    run容器: docker run -it echo/centos
    
    主机对应默认地址:
        docker inspect 容器ID
        下面这个路径就是主机对应的默认地址
        "Source": "/var/lib/docker/volumes/9299288589cc1b1ce530cdd520e48ec41c2c4c9f0b5503bc4d72af3828601db5/_data"            

#### 备注
    Docker挂载主机目录Docker访问出现cannot open directory.:Permission denied
    解决方法:在挂载目录后多加一个--privileged=true参数即可    

### 数据卷容器
    命名的容器挂载数据卷,其他容器通过挂在这个(父容器)实现数据共享,挂载数据卷的容器,称之为数据卷容器
    
    实例:
    1. 先启动一个父容器dc01 在dataVolumeContainer2新增内容
    
    2. dc02/dc03继承自dc01    --volumes-from
       命令: dc02/dc03分别在dataVolumeContainer2各自新增内容
    
    3. 回到dc01可以看到dc02/dc03各自添加的都能共享了
    
    4. 删除dc01, dc02修改后dc03可以访问
    
    5. 删除dc02 后dc03可否访问
    
    6. 新建dc04继承dc03后再删除dc03
    
    结论: 容器之间配置信息的传递,数据卷的生命周期一直持续到没有容器使用它为止 

## DockerFile解析

### DockerFile概念
    DockerFile是用来构建Docker镜像的构建文件,是由一系列命令和参数构成的脚本
#### 构建三步骤
    1. 编写Dockerfile文件
    2. docker build
    3. docker run

#### 文件结构
```dockerfile
#源镜像 相当于java 的Object
FROM scratch  
#作者邮箱
MAINTAINER The CentOS Project <cloud-ops@centos.org>
#压缩包
ADD c68-docker.tar.xz /
#说明
LABEL name="CentOS Base Image" \
    vendor="CentOS" \
    license="GPLv2" \
    build-date="2016-06-02"

# Default command
CMD ["/bin/bash"]
```    

### DockerFile构建过程解析
    
#### Dockerfile内容基础知识
    1. 每条保留字指令都必须为大写字母且后面要跟随至少一个参数
    2. 指令按照从上到下,顺序执行
    3. #表示注释
    4. 每条指令都会创建一个新的镜像层,并对镜像进行提交

#### Docker执行 Dockerfile的大致流程     
    1. docker从基础镜像运行一个容器
    2. 执行一条指令并对容器做出修改
    3. 执行类似docker commit的操作提交一个新的镜像层
    4. docker再基于刚提交的镜像运行一个新容器
    5. 执行dockerfile中的下一条指令知道所有指令都执行完成
#### 总结
    从应用软件的角度来看, Dockerfile、Docker镜像与Docker容器分别代表软件的三个不同阶段
    * Dockerfile是软件的原材料
    * Docker镜像是软件的交付品
    * Docker容器则可以认为是软件的运行态。
    Dockerfile面向开发,Docker镜像成为交付标准,Docker容器则涉及部署和运维,三者缺一不可,合力充当Docker体系的基石。

    1. Dockerfile, 需要定义一个Dockerfile, Dockerfile定义了进程需要的一切东西。Dockerfile涉及的内容包括执行代码或者是文件、环境变量、
       依赖包、运行时环境、动态链接库、操作系统的发行版、服务进程和内核进程(当应用进程需要和系统服务和内核进程打交道,这是需要考虑如何涉及namespace的权限控制)等等
    2. Docker镜像,在用Dockerfile定义一个文件之后,docker build时会产生一个Docker镜像,当运行Docker镜像时,会真正开始提供服务;
    3. Docker容器,容器是直接提供服务的。
    
### DockerFile体系结构(保留字指令)

#### FROM
    基础镜像,当前新镜像是基于哪个镜像的
#### MAINTAINER
    镜像维护者的姓名和邮箱地址
#### RUN
    容器构建时需要运行的命令
#### EXPOSE
    当前容器对外暴露出的端口
#### WORKDIR
    指定在创建容器后,终端默认登录的进来工作目录,一个落脚点
#### ENV
    用来在构建镜像过程中设置环境变量
    
    实例:
    ENV PATH /usr/mytest
    WORKDIR $PATH
#### ADD
    将宿主机目录下的文件拷贝进镜像且ADD命令会自动处理URL和解压tar压缩包

#### COPY
    类似ADD,拷贝文件和目录到镜像中
    将从构建上下文目录中<源路径>的文件/目录复制到新的一层的镜像内的<目标路径>位置
    1. COPY src dest
    2. COPY ["src", "dest"]

#### VOLUME
    容器数据卷,用于数据保存和持久化的工作

#### CMD
    指定一个容器启动时要运行的命令
    
    Dockerfile中可以有多个CMD指令,但只有最后一个生效,CMD会被docker run 之后的参数替换
    
#### ENTRYPOINT
    指定一个容器启动时要运行的命令
    
    ENTRYPOINT的目的和CMD一样,都是在指定容器启动程序及参数
    
#### ONBUILD
    当构建一个被继承的Dockerfile时运行命令,父镜像在被子镜像的ONBUILD被触发

#### 总结
BUILD   |   BOTH    |   RUN
---- | ----- | -----
FROM  |  WORKDIR    |   CMD
MAINTAINER  |   USER    |   ENV 
COPY    |       |   EXPOSE
ADD     |       |   VOLUME
RUN     |       |   ENTRYPOINT 
ONBUILD |       |
.dockerignore   |       |

### 案例

#### 1. Base镜像(scratch)
    Docker Hub 中 99% 的镜像都是通过在base镜像中安装或配置需要的软件构建出来的

#### 2. 自定义镜像mycentos

##### 编写 
    1.Hub默认CentOS镜像什么情况
        1) 初始centos运行该镜像时默认路径是/
        2) 默认不支持vim
        3) 默认不支持ifconfig
        自定义mycentos目的使我们自己的镜像具备如下:
            ① 登录后的默认路径
            ② vim编辑器
            ③ 查看网路配置ifconfig支持
    2. 编写DockerFile文件
```dockerfile
FROM centos
MAINTAINER zsf<zsf2580@163.com>
ENV path /user/local
WORKDIR $path

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80

CMD echo $path
CMD echo "success------------------ok"
CMD /bin/bash

```    
##### 构建
    docker build -t 新镜像名字:TAG .
    
    docker build -f /myDocker/dockerFile2 -t echo/centos:1.1.0 .
##### 运行
    docker run -it echo/centos:1.1.0
    
    执行pwd命令 打印:/user/local
    执行vim命令 可以编辑
    执行ifconfig 命令 打印网卡信息
    
##### 列出镜像的变更历史
    docker history 镜像ID

#### 3. CMD/ENTRYPOINT 镜像实例
    都是指定一个容器启动时要运行的命令

##### CMD
    Dockerfile中可以有多个CMD命令,但只有最后一个生效,CMD会被docker run之后的参数替换
    
    tomcat的实例:
        docker run -it -p 9980:8080 tomcat ls -l
        执行这个命令tomcat是不会启动的,只会打印 /usr/local 下面的文件信息
        因为 tomcat镜像的Dockerfile里面最后一行为: 
        CMD ["catalina.sh", "run"] 启动tomcat
        ls -l 相当于CMD ls -l 覆盖了CMD ["catalina.sh", "run"]         
        
##### ENTRYPOINT
    docker run之后的参数会被当做参数传递给ENTRYPOINT,之后形成新的命令组合
    
#### 4. 自定义镜像Tomcat9    
    mkdir -p /myDocker/tomcat9
    
    在上述目录下 touch c.txt
    
    将jdk和tomcat安装的压缩包拷贝进上一步目录
        jdk-8u121-linux-x64.tar.gz
        apache-tomcat-9.0.38.tar.gz
    
    在/myDocker/tomcat9目录下新建Dockerfile文件
```dockerfile
FROM centos
MAINTAINER zsf<zsf2580@163.com>

COPY c.txt /usr/local/cincontainer.txt

ADD jdk-8u121-linux-x64.tar.gz /usr/local/
ADD apache-tomcat-9.0.38.tar.gz /usr/local/

RUN yum -y install vim

ENV MYPATH /usr/local
WORKDIR $MYPATH

ENV JAVA_HOME /usr/local/jdk.1.8.0_121
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.38
ENV CATALINA_BASE /usr/local/apache-tomcat-9.0.38
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin

EXPOSE 8080

#ENTRYPOINT ["/usr/local/apache-tomcat-9.0.38/bin/startup.sh"]
#CMD ["/usr/local/apache-tomcat-9.0.38/bin/catalina.sh", "run"]
CMD /usr/local/apache-tomcat-9.0.38/bin/startup.sh && tail -f /usr/local/apache-tomcat-9.0.38/logs/catalina.out 

```    
    构建
    docker build -t echo/mytomcat9 .
    
    run
    docker run -d -p 9080:8080 --name myt9 
    -v /myDocker/tomcat9/test:/usr/local/apache-tomcat-9.0.38/webapps/test
    -v /myDocker/tomcat9/tomcat9logs/:/usr/local/apache-tomcat-9.0.38/logs
    --privileged=true echo/mytomcat9
    
    验证

## Docker常用安装

### 总体步骤
    1. 搜索镜像
    2. 拉取镜像
    3. 查看镜像
    4. 启动镜像
    5. 停止容器
    6. 移除容器

### 安装tomcat
    1. docker hub上面查找tomcat镜像            docker search tomcat
    2. 从docker hub上录取tomcat镜像到本地       docker pull tomcat 
    3. docker images查看是否有拉取到的tomcat
    4. 使用tomcat镜像创建容器(也叫运行镜像)

### 安装mysql
    1. docker hub上面查找mysql镜像
    2. docker hub上拉取mysql 5.6镜像到本地 docker pull mysql:5.6
    3. 使用mysql 5.6镜像创建容器
        docker run -p 12345:3306 --name mysql 
        -v /myDocker/mysql/conf:/etc/mysql/conf.d
        -v /myDokcer/mysql/logs:/logs
        -v /myDocker/mysql/data:/var/lib/mysql
        -e MYSQL_ROOT_PASSWORD=123456
        -d mysql:5.6 
        
        进入mysql交互
        docker exec -it 容器ID /bin/bash
        
        进入mysql 输入密码登录
        mysql -uroot -p
        
        数据备份
        docker exec 2dc6524b5668 sh -c 'exec mysqldump --all-databases -uroot -p "123456"' > /myDocker/all-databases.sql

### 安装redis
    1. 从docker hub拉取 redis3.2镜像到本地 docker pull redis:3.2
    2. 使用redis镜像创建容器(也叫运行镜像) 
        1) 使用镜像
            docker run -p 6379:6379
            -v /myDocker/redis/data:/data
            -v /myDocker/redis/conf/redis.conf:/usr/local/etc/redis/redis.conf
            -d redis:3.2 redis-server /usr/local/etc/redis/redis.conf --appendonly yes
        2) 在主机/myDocker/redis/conf/redis.conf 目录下新建reids.conf文件
           vim /myDocker/redis/conf/redis.conf
        3) 测试redis-cli连接 docker exec -it 容器ID redis-cli

## 本地镜像发布到阿里云

### 镜像的生成方法
    1. 前面的DockerFile
    2. 从容器创建一个新的镜像
        docker commit [OPTIONS] 容器ID [REPOSITORY[:TAG]]
### 将本地镜像推送到阿里云
    1. 本地镜像素材原型
    2. 阿里云开发者平台 https://promotion.aliyun.com/ntms/act/kubernetes.html
    3. 创建仓库镜像 命名空间 仓库名称
    4. 将镜像推送到registry
        sudo docker login --username= registry.cn-shanghai.aliyuncs.com
        sudo docker tag [ImageId] registry.cn-shanghai.aliyuncs.com/命名空间/仓库名称:[镜像版本号]
        sudo docker push registry.cn-shanghai.aliyuncs.com/命名空间/仓库名称:[镜像版本号]
    5. 公有云可以查询到

### 将阿里云上的镜像下载到本地
    git pull registry.cn-shanghai.aliyuncs.com/命名空间/仓库名称:[镜像版本号]
    

              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              