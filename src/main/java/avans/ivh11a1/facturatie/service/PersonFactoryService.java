package avans.ivh11a1.facturatie.service;

import avans.ivh11a1.facturatie.domain.Person;

/**
 * Created by kevin on 11-3-2017.
 */
public interface PersonFactoryService {
    Person getPerson(String type, int id);
}
