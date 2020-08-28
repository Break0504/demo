import time

from selenium import webdriver


driver = webdriver.Chrome()

driver.maximize_window()

r = driver.get("")

login_id = driver.find_element_by_id("loginId").send_keys("phwdstest")
pass_word = driver.find_element_by_id("loginPwd").send_keys("phwdstest123")
page_code = driver.find_element_by_id("pageCode").send_keys("123456")

driver.find_element_by_id("loginForm").submit()

time.sleep(1)
text = driver.find_element_by_id("main1").text
print(text)


