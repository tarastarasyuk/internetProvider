package com.internetProvider.aservice;

import com.internetProvider.model.Tariff;
import com.internetProvider.model.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class OwnerService {
    private final UserService userService;
    public OwnerService(HttpServletRequest request) {
        this.userService = new UserService(request);
    }

    public boolean getTariffPayment(User user, Tariff tariff) {

        BigDecimal userAccount = (user.getAccount()).subtract(tariff.getPrice());

        userService.changeUserAccountById(user.getId(), userAccount);

        User userOwner = userService.getUserOwner();

        BigDecimal userOwnerAccount = (userOwner.getAccount()).add(tariff.getPrice());
        userService.changeUserAccountById(userOwner.getId(), userOwnerAccount);

        return userService.setUserTariffById(user.getId(), tariff);
    }

}
