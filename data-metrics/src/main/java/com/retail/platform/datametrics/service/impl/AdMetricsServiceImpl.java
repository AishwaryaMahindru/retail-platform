package com.retail.platform.datametrics.service.impl;

import com.retail.platform.datametrics.model.AdClick;
import com.retail.platform.datametrics.model.AdClickKey;
import com.retail.platform.datametrics.repository.AdClickRepository;
import com.retail.platform.datametrics.service.AdMetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdMetricsServiceImpl implements AdMetricsService {

    private final AdClickRepository adClickRepository;

    @Override
    public long getAdClicksForCampaign(String tenantId, String campaignId) {
        return adClickRepository.findByTenantIdAndCampaignId(tenantId, campaignId)
                .map(AdClick::getClickCount)
                .orElse(0L);
    }

    @Override
    @Transactional
    public long incrementAdClick(String tenantId, String campaignId) {
        AdClickKey key = new AdClickKey(tenantId, campaignId);

        AdClick adClick = adClickRepository.findById(key)
                .orElse(AdClick.builder()
                        .key(key)
                        .clickCount(0L)
                        .build());

        adClick.setClickCount(adClick.getClickCount() + 1);
        adClickRepository.save(adClick);

        return adClick.getClickCount();
    }
}
