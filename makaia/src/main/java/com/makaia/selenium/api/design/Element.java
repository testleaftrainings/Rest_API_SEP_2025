package com.makaia.selenium.api.design;

import org.openqa.selenium.WebElement;

public interface Element {

	public void click(WebElement ele);

	public void click(WebElement ele, String jsExpression);

	public void type(WebElement ele, String data);

	public void typeAndEnter(WebElement ele, String data);

	public void dropdownSelectByValue(WebElement ele, String value);

	public String getElementText(WebElement ele);

	public String getAttributeValue(WebElement ele, String value);

}