package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.domain.dashboard.DashboardBox;
import avans.ivh11a1.facturatie.domain.dashboard.DashboardModel;
import avans.ivh11a1.facturatie.domain.Exception.StateException;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.repository.CustomerRepository;
import avans.ivh11a1.facturatie.repository.InsuranceRepository;
import avans.ivh11a1.facturatie.repository.TreatmentRepository;
import avans.ivh11a1.facturatie.repository.UserRepository;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import avans.ivh11a1.facturatie.service.UserService;
import com.google.common.collect.Iterables;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tinne on 27-3-2017.
 */

@Service("UserService")
@Transactional(rollbackFor = StateException.class)
public class UserServiceImplProxy implements UserService {
    final
    UserAdministrationService userAdministrationService;
    CustomerRepository customerRepository;
    InsuranceRepository insuranceRepository;
    TreatmentRepository treatmentRepository;
    private
    UserRepository userRepository;
    private UserServiceImpl trueServiceImpl;

    @Autowired
    public UserServiceImplProxy(UserRepository userRepository,CustomerRepository customerRepository, InsuranceRepository insuranceRepository, TreatmentRepository treatmentRepository, UserAdministrationService userAdministrationService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.insuranceRepository = insuranceRepository;
        this.treatmentRepository = treatmentRepository;
        this.userAdministrationService = userAdministrationService;

        this.trueServiceImpl = new UserServiceImpl(userRepository, userAdministrationService);
    }

    @Override
    public Iterable<User> findAll() {
        return trueServiceImpl.findAll();
    }

    @Override
    public User findOne(int id) {
        return trueServiceImpl.findOne(id);
    }

    @Override
    public Boolean save(User user) {
        return trueServiceImpl.save(user);
    }

    @Override
    public Boolean delete(User user) {
        return trueServiceImpl.delete(user);
    }

    @Override
    public Boolean deleteById(int id) {
        return trueServiceImpl.deleteById(id);
    }

    @Override
    public Boolean loginUser(User user) {
        return trueServiceImpl.loginUser(user);
    }

    @Override
    public void logoutUser() {
        trueServiceImpl.logoutUser();
    }

    @Override
    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public DashboardModel getDashboardData() {
        DashboardModel dashboardModel = trueServiceImpl.getDashboardData();

        if(userAdministrationService.getCurrentUser() != null) {
            Role currentRole = userAdministrationService.getCurrentUser().getRole();
            if (currentRole == Role.ADMIN || currentRole == Role.ADMINISTRATION) {
                dashboardModel.setBox1(new DashboardBox("Registered customers", Iterables.size(customerRepository.findAll())));//hoeveelheid klanten
                dashboardModel.setBox2(new DashboardBox("Registered managers", Iterables.size(userRepository.findAll())));//hoeveelheid users (met rollen)
                dashboardModel.setBox3(new DashboardBox("Total running contracts", Iterables.size(insuranceRepository.findAll())));//hoeveelheid lopende contracten
                dashboardModel.setBox4(new DashboardBox("Mail subscriptions", 0));//hoeveelheid subscribers (nieuwsbrief)
            } else if (currentRole == Role.FINANCE) {
                dashboardModel.setBox1(new DashboardBox("Total running contracts", Iterables.size(insuranceRepository.findAll())));//hoeveelheid lopende contracten
                dashboardModel.setBox2(new DashboardBox("Total monthly fee proceeds (in €)", 0));//Maandelijkse opbrengst insurances (monthly_fee)
                dashboardModel.setBox3(new DashboardBox("Average treatment time (in minutes)", 0));//Gemiddelde behandelingstijd
                dashboardModel.setBox4(new DashboardBox("Average treatment price (in €)", 0));//Gemiddelde behandelingprijs
            }
        }

        return dashboardModel;
    }
}
