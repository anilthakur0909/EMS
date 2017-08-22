/**
 * 
 */
package com.bebo.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author anthakur
 *
 */
@Aspect
public class CacheMonitor {

	private final static Logger LOG = LoggerFactory.getLogger(CacheMonitor.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Around("execution(* com.bebo.dao..*.*(..))")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		if (!LOG.isDebugEnabled()) {
			return pjp.proceed();
		}

		Statistics statistics = sessionFactory.getStatistics();
		statistics.setStatisticsEnabled(true);

		long hit0 = statistics.getQueryCacheHitCount();
		long miss0 = statistics.getSecondLevelCacheMissCount();

		Object result = pjp.proceed();

		long hit1 = statistics.getQueryCacheHitCount();
		long miss1 = statistics.getQueryCacheMissCount();

		double ratio = (double) hit1 / (hit1 + miss1);
		System.out.println(
				"*************************** here in CacheMonitor *******************************************");

		if (hit1 > hit0) {
			LOG.debug(pjp.getTarget().getClass().getName(), pjp.getSignature().toShortString());
			System.out.println("************************ here in CacheMonitor ****** hit1=" + hit1 + "   hit0=" + hit0
					+ " ratio" + ratio + " " + pjp.getTarget().getClass().getName() + "  "
					+ pjp.getSignature().toShortString());
		} else if (miss1 > miss0) {
			LOG.debug(pjp.getTarget().getClass().getName(), pjp.getSignature().toShortString());
			System.out.println("*************************** here in CacheMonitor **miss1=" + miss1 + "   miss0=" + miss0
					+ " " + pjp.getTarget().getClass().getName() + " " + pjp.getSignature().toShortString());
		} else {
			System.out.println(
					"*************************** here in CacheMonitor ******************************************* query cache not used");
			LOG.debug("query cache not used");
		}

		return result;
	}
}
