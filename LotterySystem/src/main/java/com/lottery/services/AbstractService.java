package com.lottery.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AbstractService {
	public final Logger LOG=LogManager.getLogger(this.getClass().getName());
}
