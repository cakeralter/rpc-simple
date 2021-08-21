package cc.caker.rpc.api;

import cc.caker.rpc.api.dto.BrandDTO;
import cc.caker.rpc.api.dto.BrandQueryDTO;
import cc.caker.rpc.api.dto.PageResult;
import cc.caker.rpc.api.dto.Result;

/**
 * BrandService
 *
 * @author cakeralter
 * @date 2021/8/21
 * @since 1.0
 */
public interface BrandService {

    Result<Long> saveOrUpdate();

    PageResult<BrandDTO> pageQuery(BrandQueryDTO query);
}
