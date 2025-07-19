package com.cafeteria.products.catalog.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepo productRepo;

    ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public PagedResult<Product> getProducts(int pageNo) {
        Sort sort = Sort.by("productName").ascending();

        //        Page no is changed to 0 when it is 1 or less than 1 i.e -1,-2, and so on
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;

        Pageable pageable = PageRequest.of(pageNo, 10, sort);

        Page<Product> productsPage = productRepo.findAll(pageable).map(ProductMapper::toProduct);

        return new PagedResult<>(
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious(),
                productsPage.getContent());
    }
}
