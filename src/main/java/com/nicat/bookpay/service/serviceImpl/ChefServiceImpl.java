package com.nicat.bookpay.service.serviceImpl;

import com.nicat.bookpay.entity.Chef;
import com.nicat.bookpay.enums.EnumAvaibleStatus;
import com.nicat.bookpay.exception.ExceptionConstants;
import com.nicat.bookpay.repostory.ChefDao;
import com.nicat.bookpay.request.ReqChef;
import com.nicat.bookpay.response.RespChef;
import com.nicat.bookpay.response.RespChefList;
import com.nicat.bookpay.response.RespStatus;
import com.nicat.bookpay.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {
    @Autowired
    private ChefDao chefDao;

    @Override
    public RespChefList getChefList() {
        RespChefList response = new RespChefList();
        try{
            List<RespChef> respChefList = new ArrayList<>();
            List<Chef> chefList = chefDao.findAllByActive(EnumAvaibleStatus.ACTIVE.getValue());
            if (chefList.isEmpty()){
                response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
                return response;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RespChef getChefById(Long chefId) {
        RespChef response = new RespChef();
        try{
            if (chefId == null){
                response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
                return response;
            }
            Chef chef = chefDao.findChefByChefIdAndActive(chefId,EnumAvaibleStatus.ACTIVE.getValue());
            if (chef == null){
                response.setStatus(new RespStatus(ExceptionConstants.CHEF_NOT_FOUND,"Chef not found Exception"));
            }
            response.setRespChefId(chefId);
            response.setRespActive(chef.getActive());
            response.setRespDataDate(chef.getChefDate());
            response.setRespChefName(chef.getName());
            response.setStatus(RespStatus.getSuccesMessage());
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
        }
        return response;
    }

    @Override
    public RespStatus addChef(ReqChef reqChef) {
        RespStatus status = null;
        try{
            Long id = reqChef.getReqChefId();
            String name  = reqChef.getReqName();
            if (id == null || name == null){
                return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            }
            Chef chef = new Chef();
            chef.setName(name);
            chefDao.save(chef);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus updateChef(ReqChef reqChef) {
        RespStatus status = null;
        try{
            Long id = reqChef.getReqChefId();
            String name  = reqChef.getReqName();
            if (id == null || name == null){
                return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            }
            Chef chef = chefDao.findChefByChefIdAndActive(id,EnumAvaibleStatus.ACTIVE.getValue());
            if (chef == null){
                return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            }
            chef.setName(name);
            chefDao.save(chef);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }

    @Override
    public RespStatus deleteChef(Long chefId) {
        RespStatus status = null;
        try{
            if (chefId == null){
                return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
            }
            Chef chef = chefDao.findChefByChefIdAndActive(chefId,EnumAvaibleStatus.ACTIVE.getValue());
            if (chef == null){
                return new RespStatus(ExceptionConstants.CHEF_NOT_FOUND,"Chef not found Exception");
            }
            chef.setActive(EnumAvaibleStatus.DEACTIVE.getValue());
            chefDao.save(chef);
            status = RespStatus.getSuccesMessage();
        }catch (Exception e){
            e.printStackTrace();
            return new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception");
        }
        return status;
    }
}
