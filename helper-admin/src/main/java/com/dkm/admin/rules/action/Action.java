package com.dkm.admin.rules.action;


public interface Action {
	void execute(RuleFiredEvent ruleFired);
}
