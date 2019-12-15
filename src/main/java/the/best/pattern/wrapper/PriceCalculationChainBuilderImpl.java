package the.best.pattern.wrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PriceCalculationChainBuilderImpl implements PriceCalculationChainBuilder {
    @Override
    public PriceCalculationService buildStandart() {
        log.info("Building standart chain");
        BookingPriceCalculationService bookingPriceCalculationService = new BookingPriceCalculationService();
        KmPriceCalculationService kmPriceCalculationService = new KmPriceCalculationService();
        DiscountPriceCalculationService discountPriceCalculationService = new DiscountPriceCalculationService();
        UserPointsPriceCalculationService userPointsPriceCalculationService = new UserPointsPriceCalculationService();

        bookingPriceCalculationService.setNext(kmPriceCalculationService);
        kmPriceCalculationService.setNext(discountPriceCalculationService);
        discountPriceCalculationService.setNext(userPointsPriceCalculationService);
        log.info("Builded standart chain");
        return bookingPriceCalculationService;
    }
}
