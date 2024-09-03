package com.engine.fakau.stock.request;

import java.util.List;

public record SaleProduitRequet(List<ProduitRequest> produitRequests, CarteInfoRequest carteInfoRequest) {
}
