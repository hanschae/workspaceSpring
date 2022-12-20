package com.campus.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	@RequestMapping("/ajaxView")
	public String ajaxHome() {
		return "ajax/ajaxView";
	}
	
	@RequestMapping(value="/ajaxString", produces="application/text;charset=UTF-8")
	@ResponseBody // ���ϵǴ� ���� �䰡 �ƴѵ������̴�.
	public String ajaxString(int num, String name, String tel) {
		System.out.println(num+", "+name+", "+tel);
		
		String data = "<p>��ȣ:"+num+"<br/>�̸�:"+name+"<br/>����ó :"+tel+"</p>";
		
		return data;
	}
	@RequestMapping("/ajaxObject")
	@ResponseBody
	public TestVO ajaxObject(int num, String username) {
		System.out.println(num+", "+username);
		
		TestVO vo = new TestVO();
		vo.setNo(9999);
		vo.setUsername("�迬��");
		vo.setTel("010-1357-2468");
		vo.setAddr("����� ���ʱ� ���ʵ�");
		
		return vo;
	}
	
	@RequestMapping("/ajaxList")
	@ResponseBody
	public List<TestVO> ajaxList(){
		List<TestVO> lst = new ArrayList<TestVO>();
		
		lst.add(new TestVO(1, "ȫ�浿", "010-1111-1111", "����� ������ �Ｚ��"));
		lst.add(new TestVO(2, "�̼���", "010-2222-1111", "����� ������ ��ġ��"));
		lst.add(new TestVO(3, "�������", "010-3333-1111", "����� ������ ���ﵿ"));
		lst.add(new TestVO(4, "���̳�", "010-4444-1111", "����� ������ ������"));
		
		return lst;
		
	}
	@RequestMapping("/ajaxMap")
	@ResponseBody
	public HashMap<String,TestVO> ajaxMap() {
		HashMap<String, TestVO> map = new HashMap<String, TestVO>();
		
		map.put("m1", new TestVO(100, "�̹�ȣ", "010-1111-9999", "���ı�"));
		map.put("m2", new TestVO(200, "�ڼ���", "010-2222-8888", "�߱�"));
		map.put("m3", new TestVO(300, "�ں���", "010-3333-7777", "���ϱ�"));
		
		return map;
	}
	
	@RequestMapping("/ajaxJson")
	@ResponseBody
	public String ajaxJson(int num, String name, String tel) {
		// Ŭ���̾�Ʈ������ ������ ���۵� json������
		System.out.printf("%d, %s, %s\n",num, name, tel);
		
		int code=5865;
		String productName = "��ǻ��";
		int price = 15000;
		
		//	{"code":"5865","productName":"��ǻ��","price":"15000"}
		String jsonData = "{\"code\":\""+code+"\"";
		jsonData += ",\"productName\":\""+productName+"\"";
		jsonData += ",\"price\":\""+price+"\"}";
		
		System.out.println(jsonData);
		return jsonData;
	}
	@RequestMapping(value="ajaxForm", method=RequestMethod.POST, produces="application/text;charset=UTF-8")
	@ResponseBody
	public String ajaxForm(@RequestParam("num") int num, @RequestParam("username") String username) {
		System.out.println("��ȣ > "+num);
		System.out.println("�̸� > "+username);
		
		return num+","+username;
	}
}





