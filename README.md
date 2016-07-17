//kris 
# backendDev
backend development study


Install the required the applications.
You can find the required install files in ( 百度云   ).

注意：	IDE是intelliJ ，还需安装jdk groovy tomcat mysql rabbit redits  自己下载相关sofware
        (链接: http://pan.baidu.com/s/1pKFnEkV 密码: cz36)

1.download all the software

2. Install HomeBrew. 
ps:装好homebrew后，下面的软件安装很容易，几乎都可执行 brew install ＋ software名
Open a terminal window, and execute:
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
Below is the old link, outdated as we know at around 2015-08-11
ruby -e "$(curl -fsSL https://raw.github.com/Homebrew/homebrew/go/install)"
 
3. Install JDK, file - jdk-7u60-macosx-x64.dmg.
    注意：安装完jdk后还需要设置jdk环境变量java_home 
              根目录下打开.bash_profile 加入你jdk的安装路径，即执行命令：
              vi .bash_profile 
             加入 export JAVA_HOME=$(/usr/libexec/java_home)

后面groovy 、tomcat 环境变量设置同上
GROOVY_HOME=/usr/local/opt/groovy/libexec
export PATH=$PATH:/Users/wenkangyang/Library/apache-tomcat-8.0.36/bin

4. Install Git, you can first check in your terminal window with 'git --version'.
    If there is no git installed, please use file - git-2.0.1-intel-universal-snow-leopard.dmg. (NOTE: if you've already installed the brew, just run 'brew install git' in terminal window)

5. Install Groovy, open a separate terminal window, run 'brew install groovy'. This will take a long time (30+ minutes), you could start it and go ahead with the next steps.

6. Install IntelliJ, file - ideaIU-12.1.7b.dmg.
 注意：ide是企业版 需要lisence 这边用的是公司内部的lisence，你们得自己去弄

7. Install Lombok plugins
   You could install lombok plugin directly from IntelliJ plugins management.
    a. Go to Menu IntelliJ IDEA -> Preferences
    b. Go to IDE Settings -> Plugins
    c. Click 'Browse repositories...' -> Search 'lombok'
    d. In the search result, right click on the lombok plugin and click install

8. Install SourceTree, file - SourceTree_1.9.6.dmg.

9. Install Tomcat 7, double click to unzip the file - apache-tomcat-7.0.54.tar.gz.
    or open a separate terminal window, run 'brew install tomcat’.

10. install mysql 
  open a separate terminal window, run 'brew install tomcat’.

11.install redits
open a separate terminal window, run 'brew install redits’.

12.instal rabbitmq 
open a separate terminal window, run 'brew install rabbitmq’.








