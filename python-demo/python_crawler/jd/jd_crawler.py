from selenium import webdriver
import time
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup


from python_crawler.util import csv_util

#初始化
def init():
    #实现无可视化界面
    chrome_options = Options()
    chrome_options.add_argument('--headless')
    chrome_options.add_argument('--disable-gpu')

    #设置chrome_options=chrome_options即可实现无界面
    driver = webdriver.Chrome(options=chrome_options)

    #浏览器实现全屏
    driver.maximize_window()
    return driver

def find_all(driver, url):
    #发起请求
    driver.get(url)
    time.sleep(1)

    #操作滚轮滑到最底部,加载浏览器所有数据
    driver.execute_script("window.scrollTo(0,document.body.scrollHeight);")
    time.sleep(1)

    #得到所有代码
    source = driver.page_source
    return source

def download(source):
    bs = BeautifulSoup(source, "lxml")
    list = bs.find_all(class_="gl-item")
    data_list = []

    for li in list:
        data_id = li["data-sku"]
        print(f"商品id:{data_id}")
        img_url = "https:" + li.find("img")["src"]
        print(f"商品图片url:{img_url}")
        title = li.find(class_="p-name p-name-type-2").find("em").text
        print(f"商品名字:{title}")
        price = li.find(class_="p-price").find("i").text
        print(f"商品价格:{price}")
        #https://item.jd.com/100009082500.html
        url = "https:" + li.find(class_="p-name p-name-type-2").find("a")["href"]
        print(f"商品链接:{url}")
        comment = li.find(class_="p-commit").find("strong").text
        print(f"商品评价数量:{comment}")

        row = []
        row.append(data_id)
        row.append(title)
        row.append(img_url)
        row.append(price)
        row.append(comment)
        row.append(url)
        data_list.append(row)

    return data_list



def write(all_list):
    csv_writer = csv_util.write_csv("京东商品详情1.csv", ["商品id", "商品名字", "商品图片", "商品价格", "商品评价数量", "商品链接"])
    for data in all_list:
        csv_util.write_data(csv_writer, data)

if __name__ == '__main__':
    s = 1
    num = 1
    driver = init()
    all_list = []
    for i in range(10):
        url = "https://search.jd.com/Search?keyword=手机&wq=手机&page=" + str(num) + "&s=" + str(s) + "&click=0"
        s += 50
        num += 2
        print(url)
        source = find_all(driver, url)
        data_list = download(source)
        all_list += data_list
    write(all_list)


