package com.dkm.admin.tulip.schedule.notify;/*
package com.guohuai.tulip.schedule.notify;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.guohuai.rules.config.DroolsContainerHolder;

@Service
@Transactional
public class AsynchEventService {

	@Value("${async.pool.size:10}")
	int asyncPoolSize;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	@Autowired
	DroolsContainerHolder containerHolder;
	
	@Autowired
	private AsynchEventDao asynchEventDao;
	
	public List<AsynchEventEntity> getEventList(String eventType){
		return asynchEventDao.getUnsentEventList(eventType);
	}
	
	public int updateAsynchEvent(String oid){
		return asynchEventDao.updateEventTosendSuccess(oid);
	}
	
	public void saveEvent(AsynchEventEntity entity){
		asynchEventDao.save(entity);
	}
	
}
*/
