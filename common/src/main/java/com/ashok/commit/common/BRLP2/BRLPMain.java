package com.ashok.commit.common.BRLP2;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: TODO
 * Author: xk
 * Date: 2020/3/29 13:41
 */
public class BRLPMain {

    /**
     * @param instance 文件源数据
     */
    public static void findNode(Instance instance) {

        List<String> finalList = new ArrayList<>();
        //当前待选目标组
        List<String> targetList = new ArrayList<>(16);

        for (int i = 0; i < instance.getInit().length; i++) {
            for (int j = 0; j < instance.getHeight_yard()[i]; j++) {
                if ("0".equals(instance.getInit()[i][j].split("_")[1])) {
                    targetList.add(instance.getInit()[i][j]);
                }
            }
        }
        System.out.println(JSONObject.toJSONString(finalList));
        System.out.println(JSONObject.toJSONString(targetList));
        System.out.println("start:");
        searchTarget(instance, targetList, finalList);
        System.out.println("end");
    }

    /**
     * 节点查询
     *
     * @param ins
     * @param targetList
     */
    public static void searchTarget(Instance ins, List<String> targetList, List<String> finalList) {

        //当前待选目标组所有需要最小移动步数
        int thisMinStep = ins.YH;
        //当前最小数下标
        int thisMinIndex = -1;
        int thisMinIndexY = -1;

        int thisTargetIndex = -1;

        for (int i = 0; i < targetList.size(); i++) {

            for (int j = 0; j < ins.getInit().length; j++) {

                for (int k = 0; k < ins.height_yard[j]; k++) {
                    if (targetList.get(i).equals(ins.getInit()[j][k])) {
                        //当前需要移动步数
                        int noeRemoveStop = ins.height_yard[j] - 1 - k;
                        if (noeRemoveStop < thisMinStep) {
                            thisMinStep = noeRemoveStop;
                            thisMinIndex = j;
                            thisMinIndexY = k;
                            thisTargetIndex = i;
                        }
                    }
                }
            }
        }

        if (thisMinIndex == -1 && thisMinIndexY == -1 && thisTargetIndex == -1) {
            return;
        }
        finalList.add(ins.getInit()[thisMinIndex][thisMinIndexY]);
        removeInitData(ins, thisMinIndex, thisMinIndexY);
        removeInitAndTarget(ins, targetList, thisTargetIndex);

        System.out.println(JSONObject.toJSONString(finalList));
        System.out.println(JSONObject.toJSONString(targetList));
        System.out.println(finalList.size());
        System.out.println("X=" + thisMinIndex + "Y=" + thisMinIndexY + "init:" + ins.getHeight_yard()[thisMinIndex]);
        if (targetList.size() > 0) {
            searchTarget(ins, targetList, finalList);
        }

    }

    /**
     * 数据源target模板处理
     *
     * @param instance
     * @param targets
     * @param thisTargetIndex 当前target下标
     */
    public static void removeInitAndTarget(Instance instance, List<String> targets, int thisTargetIndex) {
        String targetArr[] = targets.get(thisTargetIndex).split("_");
        int index = targetArr[0].toCharArray()[0] - (char) 'A';
        int[] limitValid = instance.getLimited_height_vessel();
        limitValid[index] = limitValid[index] - 1;
        if (limitValid[index] <= 0) {
            if (targets.size() > 0) {
                targets.remove(thisTargetIndex);
            }
        } else {
            targets.set(thisTargetIndex, targetArr[0] + "_" + (Integer.parseInt(targetArr[1]) + 1));
        }
    }

    /**
     * 移除数据源init值以及修改每列当前最大值
     *
     * @param instance
     * @param i        横坐标
     * @param j        纵坐标
     */
    public static void removeInitData(Instance instance, int i, int j) {
        //不在栈顶的数值需要移动
        if (j != instance.getHeight_yard()[i] - 1) {
            findRecentAndMinInit(instance, i, j, 0);
        }
        instance.getHeight_yard()[i] = instance.getHeight_yard()[i] - 1;
        //每列前移
        for (int k = j; k < instance.getInit()[i].length - 1; k++) {
            instance.getInit()[i][k] = instance.getInit()[i][k + 1];
        }

    }

    /**
     * 位置移动每次移动到最小列
     *
     * @param instance
     * @param indexX         当前需要移动列
     * @param hasSearchIndex 已经查找下标
     */
    public static void findRecentAndMinInit(Instance instance, int indexX, int indexY, int hasSearchIndex) {
        String removeValue = instance.init[indexX][indexY];
        String removeArr[] = removeValue.split("_");
        int thisMin = instance.YH;
        int thisMinIndex = -1;
        for (int i = hasSearchIndex; i < instance.getInit().length; i++) {
            //当前列跳过
            if (i == indexX) {
                continue;
            }
            //已经最小列
            if (instance.getHeight_yard()[i] == 0) {
                thisMin = 0;
                thisMinIndex = i;
                break;
            }
            //每次最小时判断是否包含同字母
            if (instance.getHeight_yard()[i] < thisMin) {
                boolean exist = false;
                for (int k = 0; k <= instance.getHeight_yard()[i] - 1; k++) {
                    //存在
                    if (removeArr[0].equals(instance.getInit()[i][k].split("_")[0])) {
                        exist = true;
                        break;
                    }
                }
                if (!exist) {
                    thisMin = instance.getHeight_yard()[i];
                    thisMinIndex = i;
                }

            }

        }

        //防止indexX = hasSearchIndex问题 如果indexX是最小那个 就右移到小于限高最近处 indexx最大就左移（之所以这样做是 假设C 在每一组都有出问题）
        //优化点 如果c在每一组都有  取最小的那个  太过复杂
        if (thisMinIndex == -1) {
            if (indexX == 0) {
                for (int k = indexX + 1; k < instance.YS; k++) {
                    if (instance.getHeight_yard()[k] < instance.YH) {
                        thisMinIndex = k;
                        break;
                    }
                }
            } else if (indexX == instance.YS - 1) {
                for (int k = indexX - 1; k >= 0; k--) {
                    if (instance.getHeight_yard()[k] < instance.YH) {
                        thisMinIndex = k;
                        break;
                    }
                }
            }
        }

        instance.getInit()[thisMinIndex][instance.getHeight_yard()[thisMinIndex]] = removeValue;
        instance.getHeight_yard()[thisMinIndex] = instance.getHeight_yard()[thisMinIndex] + 1;
    }

}
