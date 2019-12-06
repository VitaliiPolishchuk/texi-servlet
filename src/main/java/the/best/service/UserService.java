package the.best.service;

import the.best.entity.User;

public interface UserService {

    public static final int POINTS_REQUIRE_TO_DISCOUNT = 100;

    User validate(User user);
    void updateUserPoints(User user, int price, boolean isUsedDiscount);
}
