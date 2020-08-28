import time

from selenium import webdriver

driver = webdriver.Chrome()

#最大化浏览器
driver.maximize_window()

#打开网页
driver.get("")

#设置浏览器大小
#driver.set_window_size(480, 800)

"""
#新开一个窗口，通过执行js来新开一个窗口
js = "window.open('')"
driver.execute_script(js)
"""

"""
driver.get("http://www.baidu.com")
driver.back()    #后退
driver.forward() #前进
"""

title = driver.title #网页标题
print(title)

url = driver.current_url #获取当前输入栏的url地址
print(url)

driver.refresh() #刷新页面

size = driver.get_window_size() #获取当前窗口大小
print(size)


element = driver.find_element_by_name("loginId").send_keys("phwdstest")
element = driver.find_element_by_name("loginPwd").send_keys("phwdstest123")
#element = driver.find_element_by_name("pageCode").send_keys("123456")
time.sleep(2)
pageCodeImg = driver.find_element_by_id("pageCodeImg")
#pageCodeImg = driver.find_elements_by_xpath('//*[@id="pageCodeImg"]/img')
location = pageCodeImg.location  #获取验证码x,y轴坐标
print(location)

src = driver.find_element_by_id("pageCodeImg").find_element_by_tag_name("src")


size = pageCodeImg.size  #获取验证码的长宽
print(size)
print(pageCodeImg)


#driver.execute_script("window.open('https://www.baidu.com')")
#driver.close() #关闭当前的活动窗口

time.sleep(5)

driver.quit() #退出浏览器
