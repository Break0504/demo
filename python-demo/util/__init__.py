import time

from selenium import webdriver
from time import sleep
import unittest
from PIL import Image
from PIL import ImageEnhance
import pytesseract

driver = webdriver.Chrome()
driver.maximize_window()
url = ""   #一般在登录首页
driver.get(url)
login_id = driver.find_element_by_id("loginId").send_keys("phwdstest")
pass_word = driver.find_element_by_id("loginPwd").send_keys("phwdstest123")
time.sleep(1)

driver.save_screenshot(r"D:\\image\\aa.png")  #截取当前网页，该网页有我们需要的验证码
imgelement = driver.find_element_by_id("pageCodeImg")  #定位验证码
#imgelement = driver.find_elements_by_xpath('//*[@id="pageCodeImg"]/img')
location = imgelement.location  #获取验证码x,y轴坐标

size = imgelement.size  #获取验证码的长宽
coderange=(int(location['x']),int(location['y']), int(location['x'] + 92),
           int(location['y']+size['height'])) #写成我们需要截取的位置坐标
i = Image.open(r"D:\\image\\aa.png") #打开截图
frame4 = i.crop(coderange)  #使用Image的crop函数，从截图中再次截取我们需要的区域
frame4.save(r"D:\\image\\aa.png")    #保存截图
i2 = Image.open(r"D:\\image\\aa.png")  #打开截图
imgry = i2.convert('L')   #图像加强，二值化，PIL中有九种不同模式。分别为1，L，P，RGB，RGBA，CMYK，YCbCr，I，F。L为灰度图像
sharpness = ImageEnhance.Contrast(imgry) #对比度增强
i3 = sharpness.enhance(3.0)  #3.0为图像的饱和度
i3.save(r"D:\\image\\image_code.png")
i4 = Image.open(r"D:\\image\\image_code.png")

str = pytesseract.image_to_string(i4)
text = str.strip()

text = pytesseract.image_to_string(i4).strip() #使用image_to_string识别验证码
print(text.replace(' ', ''))   #该方法的作用为 将图片内容中的空格去除