package com.jyd.common;

import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

public class cc  extends ConditionalTagSupport
{
	  private boolean test;

	  public cc()
	  {
	    init();
	  }

	  public void release()
	  {
	    super.release();
	    init();
	  }

	  protected boolean condition()
	  {
	    return this.test;
	  }

	  public void setTest(boolean test)
	  {
	    this.test = test;
	  }

	  private void init()
	  {
	    this.test = false;
	  }
	}