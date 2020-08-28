# 元素的常用操作
import time

from selenium import webdriver

driver = webdriver.Chrome()

driver.maximize_window()

#driver.implicitly_wait(10)  # 设置全局隐性等待时间，单位秒

driver.get("")

time.sleep(1)
driver.find_element_by_id("loginId").send_keys("phwdstest")
time.sleep(1)
driver.find_element_by_id("loginPwd").send_keys("phwdstest123")
time.sleep(1)
driver.find_element_by_id("pageCode").send_keys("123456")
time.sleep(1)

element = driver.find_element_by_id("loginId")

#element.get_attribute('属性名称') 获取标签的属性值
#element.get_property('属性名称') 获得元素的固有属性值，强调“专”
#element.is_selected() 元素是否被选中，用于检测复选框或单项按钮是否被勾选
#element.is_displayed() 判定改元素是否可见
#element.is_enabled() 元素是否可用
#element.size 元素的大小

print(f"搜索框的内容为:{element.get_attribute('value')}")
print(f"搜索框的class属性为:{element.get_attribute('class')}")
print(f"搜索框的type属性为:{element.get_attribute('type')}")
print(f"搜索框的坐标位置为:{element.location}")
print(f"搜索框是否可操作:{element.is_displayed()}")



driver.find_element_by_id("loginForm").submit()  # submit() 提交表单，可以是提交按钮，也可以是表单元素，也可以是输入框元素


"""
智能显性等待WebDriverWait
WebDriverWait(driver, timeout, poll_frequency=0.5, ignored_exceptions=None)
    driver 为webdriver驱动
    timeout 最长超时时间，单位(秒)
    poll_frequency 循环查找元素每次间隔的时间，默认0.5秒
    ignored_exceptions 超时后需要输出的异常信息

WebDriverWait(driver, timeout).until(method, message='')
method 函数或者实例__call__()方法返回True时停止，否则超时后抛出异常。
method 在等待时间内调用的方法或者函数，该方法或函数需要有返回值，并且只接收一个参数driver。
message 超时时抛出TimeoutException，将message传入异常显示出来

WebDriverWait(driver, timeout).until_not(method, message='')
于上面的until() 相反，until_not 中的method函数或者实例__call__()方法返回False结束，否则抛出异常。

WebDriverWait(driver,5).until(lambda driver:driver.find_element_by_id('query'))
5秒内等待元素(id='query')出现，lambda driver:driver.find_element_by_id('query') 为一个匿名函数，只有一个driver参数，返回的是查找的元素对象。

WebDriverWait(driver, 5).until_not(lambda driver:driver.find_element_by_name('query'))
5秒内等待元素消失，同示例1 until_not 要求无元素返回即元素不存在于该页面。


定义类中的__call()__方法
class wait_element(object):
   def __init__(self, locator):
       self.locator = locator

   def __call__(self, driver):
       return driver.find_element(self.locator)

WebDriverWait(driver, 5).until(wait_element((By.ID, 'query')))  # 等待元素出现
WebDriverWait(driver, 5).until_not(wait_element((By.ID, 'query')))  # 等待元素消失


4、expected_conditions 类库
from selenium.webdriver.support import expected_conditions as ec  # 引入包

ec.title_is(‘title’) 判断页面标题等于title
ec.title_contains(‘title’) 判断页面标题包含title
ec.presence_of_element_located(locator) 等待locator元素是否出现
ec.presence_of_all_elements_located(locator) 等待所有locator元素是否出现
ec.visibility_of_element_located(locator) 等待locator元素可见
ec.invisibility_of_element_located(locator) 等待locator元素隐藏
ec.visibility_of(element) 等待element元素可见
ec.text_to_be_present_in_element(locator,text) 等待locator的元素中包含text文本
ec.text_to_be_present_in_element_value(locator,value) 等待locator元素的value属性为value
ec.frame_to_be_available_and_switch_to_it(locator) 等待frame可切入
ec.element_to_be_clickable(locator) 等待locator元素可点击

等待元素被选中，一般用于复选框,单选框
ec.element_to_be_selected(element) 等待element元素是被选中
ec.element_located_to_be_selected(locator) 等待locator元素是被选中ec.element_selection_state_to_be(element, is_selected) 等待element元素的值被选中为is_selected(布尔值)
ec.element_located_selection_state_to_be(locator,is_selected) 等待locator元素的值是否被选中is_selected(布尔值)

五、什么时候使用等待
固定等待sleep与隐性等待implicitly_wait尽量少用，它会对测试用例的执行效率有影响。
显性的等待WebDriverWait可以灵活运用，什么时候需要用到？
1、页面加载的时候，确认页面元素是否加载成功可以使用WebDriverWait
2、页面跳转的时候，等待跳转页面的元素出现，需要选一个在跳转前的页面不存在的元素
3、下拉菜单的时候，如上百度搜索设置的下拉菜单，需要加上个时间断的等待元素可点击
4、页面刷新的时候
总之，页面存在改变的时候；页面上本来没的元素，然后再出现的元素


"""