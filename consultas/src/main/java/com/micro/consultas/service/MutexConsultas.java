package com.micro.consultas.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

@Service
public class MutexConsultas {

    private Map<Long, Lock> consultasEmAnalise = new HashMap<Long, Lock>();

    public void bloqueia(Long consultaId) {
        Lock lock = new ReentrantLock();
        consultasEmAnalise.put(consultaId, lock);
        lock.lock();
    }

    public void desbloqueia(Long consultaId) {
        Lock lock = consultasEmAnalise.get(consultaId);
        if (lock != null) {
            lock.unlock();
        }
        consultasEmAnalise.remove(consultaId);
    }

}
