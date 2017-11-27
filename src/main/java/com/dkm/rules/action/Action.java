package com.dkm.rules.action;


public interface Action {
	void execute(RuleFiredEvent ruleFired);
}
