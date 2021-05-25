package com.example.demo.sort.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.sort.entity.OneSort;
import com.example.demo.sort.entity.Sort;
import com.example.demo.sort.entity.TwoSort;
import com.example.demo.sort.mapper.SortMapper;
import com.example.demo.sort.service.SortService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @ClassName ServiceImpl
 * @Author zengyi
 * @Description
 * @Date 2021/3/23 9:24
 **/
@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {

    @Autowired
    private SortMapper sortMapper;

    @Override
    public List querySortList() {
        //获取一级分类list
        LambdaQueryWrapper<Sort> oneWrapper = new LambdaQueryWrapper<>();
        oneWrapper.eq(Sort::getParentId, 0);
        List<Sort> OneSortList = sortMapper.selectList(oneWrapper);
        //获取二级分类list
        LambdaQueryWrapper<Sort> twoWrapper = new LambdaQueryWrapper<>();
        oneWrapper.ne(Sort::getParentId, 0);
        List<Sort> TwoSortList = sortMapper.selectList(twoWrapper);

        List<OneSort> returnList=new ArrayList<>();
        for (Sort oSort : OneSortList) {
            OneSort oneSort = new OneSort();
            //将oSort对象复制给oneSort
            BeanUtils.copyProperties(oSort,oneSort);
            returnList.add(oneSort);

            List<TwoSort> twoReturnList=new ArrayList<>();
            for (Sort tSort : TwoSortList) {
                if(oSort.getId().equals(tSort.getParentId())){
                    TwoSort twoSort = new TwoSort();
                    BeanUtils.copyProperties(tSort,twoSort);
                    twoReturnList.add(twoSort);
                }
            }
            oneSort.setChildren(twoReturnList);
        }
        return returnList;
    }
}
