package br.com.clubelinkar.support.notification

import br.com.clubelinkar.api.company.Company

/**
 * Created by felipe on 3/13/16.
 */
interface INotificationService {
    void notifyCompanyCreation(Company newCompany)
}