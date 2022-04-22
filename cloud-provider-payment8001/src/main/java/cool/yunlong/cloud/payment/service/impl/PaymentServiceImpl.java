package cool.yunlong.cloud.payment.service.impl;

import cool.yunlong.cloud.commons.base.BaseMapper;
import cool.yunlong.cloud.commons.base.BaseServiceImpl;
import cool.yunlong.cloud.commons.entity.Payment;
import cool.yunlong.cloud.payment.dao.PaymentMapper;
import cool.yunlong.cloud.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yunlong
 * @since 2022/4/22 0:16
 */
@Service
public class PaymentServiceImpl extends BaseServiceImpl<Payment> implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    protected BaseMapper<Payment> getEntityMapper() {
        return paymentMapper;
    }


}
