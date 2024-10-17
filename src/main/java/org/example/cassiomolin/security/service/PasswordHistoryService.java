package org.example.cassiomolin.security.service;


import org.example.cassiomolin.user.domain.User;
import org.example.dao.PasswordHistoryDao;
import org.example.model.PasswordHistory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PasswordHistoryService {
    @EJB
    PasswordHistoryDao passwordHistoryDao;
    public boolean isPasswordChanged(User user){
        List<PasswordHistory> passwordHistoryList = passwordHistoryDao.listByUserId(null, null, user.getId());
        if(!passwordHistoryList.isEmpty()){
            return  passwordHistoryList.get(0).getIsPasswordChanged();
        }else {
            PasswordHistory passwordHistory = new PasswordHistory(); // if the user is not registed on user history
            passwordHistory.setUserId(user.getId());
            passwordHistory.setIsPasswordChanged(false);
            passwordHistory.setInitialPassword(user.getPassword());
            passwordHistoryDao.create(passwordHistory);
            return passwordHistory.getIsPasswordChanged();
        }
    }

}
