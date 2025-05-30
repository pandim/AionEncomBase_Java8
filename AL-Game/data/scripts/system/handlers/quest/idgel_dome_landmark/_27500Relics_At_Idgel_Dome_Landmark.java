/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen, ArchSoft,   *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package quest.idgel_dome_landmark;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestDialog;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.world.zone.ZoneName;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

public class _27500Relics_At_Idgel_Dome_Landmark extends QuestHandler {

    private final static int questId = 27500;
    public _27500Relics_At_Idgel_Dome_Landmark() {
        super(questId);
    }
	
	public void register() {
		qe.registerQuestNpc(806264).addOnQuestStart(questId); //�?�마리넨.
		qe.registerQuestNpc(806264).addOnTalkEvent(questId); //�?�마리넨.
		qe.registerQuestNpc(806265).addOnTalkEvent(questId); //�?�?��?�세네.
		qe.registerOnEnterZone(ZoneName.get("IDLDF5_FORTRESS_WAR_Q27500_A_301680000"), questId);
		qe.registerOnEnterZone(ZoneName.get("IDLDF5_FORTRESS_WAR_Q17500_B_301680000"), questId);
	}
	
	@Override
    public boolean onDialogEvent(QuestEnv env) {
        Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
        int targetId = env.getTargetId();
        if (qs == null || qs.getStatus() == QuestStatus.NONE) {
			if (targetId == 806264) { //�?�마리넨.
				switch (env.getDialog()) {
                    case START_DIALOG: {
                        return sendQuestDialog(env, 4762);
					} case ACCEPT_QUEST_SIMPLE: {
						return sendQuestStartDialog(env);
					} case REFUSE_QUEST_SIMPLE: {
				        return closeDialogWindow(env);
					}
                }
			}
		} else if (qs.getStatus() == QuestStatus.START) {
			if (targetId == 806265) { //�?�?��?�세네.
				switch (env.getDialog()) {
                    case START_DIALOG: {
                        return sendQuestDialog(env, 1353);
					} case STEP_TO_2: {
						changeQuestStep(env, 1, 2, false);
						return closeDialogWindow(env);
					}
                }
			}
		} else if (qs.getStatus() == QuestStatus.REWARD) {
            if (targetId == 806264) { //�?�마리넨.
                if (env.getDialog() == QuestDialog.START_DIALOG) {
                    return sendQuestDialog(env, 10002);
                } else {
                    return sendQuestEndDialog(env);
                }
            }
        }
		return false;
	}
	
	@Override
    public boolean onEnterZoneEvent(QuestEnv env, ZoneName zoneName) {
        Player player = env.getPlayer();
        QuestState qs = player.getQuestStateList().getQuestState(questId);
        if (qs != null && qs.getStatus() == QuestStatus.START) {
            int var = qs.getQuestVarById(0);
			if (zoneName == ZoneName.get("IDLDF5_FORTRESS_WAR_Q27500_A_301680000")) {
				if (var == 0) {
					changeQuestStep(env, 0, 1, false);
					return true;
				}
			} else if (zoneName == ZoneName.get("IDLDF5_FORTRESS_WAR_Q17500_B_301680000")) {
				if (var == 2) {
					qs.setQuestVar(3);
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);
					return true;
				}
			}
		}
		return false;
	}
}