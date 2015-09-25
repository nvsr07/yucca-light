/**
 * 
 */

var EventStateEnum = [ 
                       'GW_RECEIVED', 
                       'SENDING_RT_PROGRESS', 
                       'SENDING_FAILED', 
                       'SENT_RT', 
                       'SENDING_A2A_PROGRESS', 
                       'SENT_A2A', 
                       'SENT_INVALID'
];

var workflowStateObj = {
		s0_gw_received: {
			name: "yucca_light.gw_received",
			countMessages:0,
			state: EventStateEnum[0],
			lastmessage: {
				payload: { },
				headers: { }
			},
			messages: []
		},
		s1_sending_rt_progress: {
			name: "yucca_light.sending_rt_progress",
			countMessages: 0,
			state: EventStateEnum[1],
			lastmessage: {
				payload: { },
				headers: { }
			},
			messages: []
		},
		s2_sent_rt: {
			name: "yucca_light.sent_rt",
			countMessages: 0,
			state: EventStateEnum[3],
			lastmessage: {
				payload: { },
				headers: { }
			},
			messages: []
		},
		s3_sending_failed: {
			name: "yucca_light.sending_failed",
			countMessages: 0,
			state: EventStateEnum[2],
			lastmessage: {
				payload: { },
				headers: { }
			},
			messages: []
		},
		s4_sending_a2a_progress: {
			name: "yucca_light.sending_a2a_progress",
			countMessages: 0,
			state: EventStateEnum[4],
			lastmessage: {
				payload: { },
				headers: { }
			},
			messages: []
		},
		s5_sent_a2a: {
			name: "yucca_light.sent_a2a",
			countMessages: 0,
			state: EventStateEnum[5],
			lastmessage: {
				payload: { },
				headers: { }
			},
			messages: []
		},
		s6_sent_invalid: {
			name: "yucca_light.sent_invalid",
			countMessages: 0,
			state: EventStateEnum[6],
			lastmessage: {
				payload: { },
				headers: { }
			},
			messages: []
		}
};

var workflowErrorStateObj = {
		
}