package com.shiva.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;


import java.util.Optional;

/**
 * We are writing this class to track who is created and updated data. Mainly for two fields we are doing this CreatedBy and updatedBy for these two fields in our db
 **/

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS"); /** For Now We are hard code this value later we have to fix it when we work on spring security**/
        /** At the time of create CreatedBy get "ACCOUNTS_MS" value and at the time of update "updatedBy" field get  "ACCOUNTS_MS" value**/
    }
}
