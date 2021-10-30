package com.internetProvider.model;

public class TariffHasService {
    private int tariffId;
    private int serviceId;

    public TariffHasService() {
    }

    public TariffHasService(int tariffId, int serviceId) {
        this.tariffId = tariffId;
        this.serviceId = serviceId;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
