package com.demo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author: Created by zhangsf
 * @datetime: Created in 16:30 2020/9/2
 * @description:
 */
public class T {


    @Test
    void contextLoads() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        testBrowser(driver);

    }

    private void testBrowser(WebDriver driver) throws Exception {
        driver.get("");

        Thread.sleep(200);

        //浏览器最大化
        driver.manage().window().maximize();

        Thread.sleep(200);

        driver.findElement(By.id("loginId")).sendKeys("phwdstest");
        driver.findElement(By.id("loginPwd")).sendKeys("phwdstest123");
        driver.findElement(By.id("pageCode")).sendKeys("123");
        // 清空输入框
        //element.clear();
        // 获取输入框的内容
        //element.getAttribute("value");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/table/tbody/tr[6]/td/span[1]/input"));
        button.click();

        Thread.sleep(2000);

        //进入iframe 菜单栏
        driver.switchTo().frame("mainFrame");
        driver.switchTo().frame("frmleft");

        //点击事件跟进菜单
        WebElement event = driver.findElement(By.xpath("//*[@id=\"main1\"]/td/ul/li[10]/a"));
        event.click();

        Thread.sleep(2000);

        //回到主窗口
        driver.switchTo().defaultContent();

        //进入正文 frame
        driver.switchTo().frame("mainFrame");
        driver.switchTo().frame("I1");

        //选择下拉框
        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[4]/td/select")));

        // 选择对应的选择项， index 从0开始的
        //select.selectByIndex(2);
        //select.selectByValue("18");
        select.selectByVisibleText("客服事件");

        WebElement query = driver.findElement(By.xpath("//*[@id=\"query\"]"));
        query.click();

        Thread.sleep(2000);

        //测试提交反馈
        //testFeedBack(driver);

        //下载事件内容
        //downEvent(driver);

        //driver.quit();
    }


    private void downEvent(WebDriver driver) {


        WebElement down = driver.findElement(By.xpath("//*[@id=\"queryForm\"]/button[2]"));
        down.click();
    }

    private void testFeedBack(WebDriver driver) throws InterruptedException {
        // 判断按钮是否enable
        //addButton.isEnabled();


        WebElement feedBack = driver.findElement(By.xpath("/html/body/div[2]/div/table/tbody/tr[3]/td[2]/a[2]"));
        feedBack.click();
        Thread.sleep(2000);

        WebElement text = driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td/textarea"));
        text.sendKeys("测试自动化反馈1");

        //反馈内容提交
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"addTr\"]"));
        submit.click();
        Thread.sleep(2000);

        WebElement back = driver.findElement(By.xpath("//*[@id=\"right\"]/a"));
        back.click();
    }

    private void radioButton() {
        // 找到单选框元素
        String xpath="//input[@type='radio'][@value='Apple']";
        //WebElement apple = driver.findElement(By.xpath(xpath));

        //选择某个单选框
        //apple.click();

        //判断某个单选框是否已经被选择
        //boolean isAppleSelect = apple.isSelected();

        // 获取元素属性
        //apple.getAttribute("value");
    }
}
