package com.jyd.controller;




import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.jyd.vo.JsonBody;




@Controller
public class Hello {
	@RequestMapping(value = "/asyn")
	@ResponseBody
	public WebAsyncTask<JsonBody> asyn(Model model) {
		System.out.println("main thread id is : " + Thread.currentThread().getId());
		
		Callable<JsonBody> callable = new Callable<JsonBody>() {
			public JsonBody call() throws Exception {
				Thread.sleep(3000); 
				JsonBody bd = new JsonBody();
				bd.setMsg(""+Thread.currentThread().getId());
				bd.setCode(0);
				return bd;
			}
		};
		return new WebAsyncTask<JsonBody>(callable);
	}
	
	@RequestMapping(value = "/syn")
	@ResponseBody
	public JsonBody syn() throws InterruptedException{
		Thread.sleep(3000); //
		JsonBody bd = new JsonBody();
		bd.setMsg("ͬ"+Thread.currentThread().getId());
		bd.setCode(0);
		return bd;
	}

}