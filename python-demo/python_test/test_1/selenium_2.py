

"""
八种属性定位页面元素：
By.ID
By.XPATH
By.LINK_TEXT
By.PARTIAL_LINK_TEXT
By.NAME
By.TAG_NAME
By.CLASS_NAME
By.CSS_SELECTOR
"""
import time

from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

driver.get("")

driver.find_element(By.ID, "loginId").send_keys("123456")

driver.find_element_by_id('元素id属性')  # ------ 最常用，简单
driver.find_element_by_name('元素name属性')  # ------ 最常用，简单
driver.find_element_by_class_name('元素class属性')  # ------ 易重复，看情况使用
driver.find_element_by_tag_name('元素标签名')  # ------ 最不靠谱
driver.find_element_by_link_text('链接文本')  # ------ 精确匹配链接 （<a> 标签中的文字）
driver.find_element_by_partial_link_text('部分链接文本')  # ------ 模糊匹配链接
driver.find_element_by_xpath()  # ------ 最灵活，万能的灵药  xpath 可以根据元素的父节点或者哥哥弟弟节点定位到元素
driver.find_element_by_css_selector()  # ------ css定位 没xpath灵活


time.sleep(1)
driver.quit()

